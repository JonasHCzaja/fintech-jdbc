package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.fintech.bean.Ganho;
import br.com.fiap.fintech.dao.GanhoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

@WebServlet("/ganho")
public class GanhoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private GanhoDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = DAOFactory.getGanhoDAO();
    }

    public GanhoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        switch (acao) {
            case "listar":
                listar(request, response);
                break;
            case "abrir-form-edicao":
                String cdGanhoStr = request.getParameter("cdGanho");
                if (cdGanhoStr != null && !cdGanhoStr.isEmpty()) {
                    try {
                        int cdGanho = Integer.parseInt(cdGanhoStr);
                        Ganho ganho = dao.buscar(cdGanho);
                        request.setAttribute("ganho", ganho);
                        request.getRequestDispatcher("edicao-ganho.jsp").forward(request, response);
                    } catch (NumberFormatException e) {
                        request.setAttribute("erro", "Código de ganho inválido.");
                        listar(request, response);
                    }
                } else {
                    request.setAttribute("erro", "Código de ganho não fornecido.");
                    listar(request, response);
                }
                break;
            default:
                listar(request, response);
                break;
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
        String idUsuario = (String) session.getAttribute("user");
        List<Ganho> lista = dao.listar(idUsuario);
        System.out.println("Número de ganhos: " + lista.size());
        request.setAttribute("ganhos", lista);
        request.getRequestDispatcher("lista-ganho.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(request, response);
                break;
            case "editar":
                String cdGanhoStr = request.getParameter("cdGanho");
                if (cdGanhoStr != null && !cdGanhoStr.isEmpty()) {
                    try {
                        int cdGanho = Integer.parseInt(cdGanhoStr);
                        editar(request, response, cdGanho);
                    } catch (NumberFormatException e) {
                        request.setAttribute("erro", "Código de ganho inválido.");
                        listar(request, response);
                    }
                } else {
                    request.setAttribute("erro", "Código de ganho não fornecido.");
                    listar(request, response);
                }
                break;
            case "excluir":
                excluir(request, response);
                break;
            default:
                listar(request, response);
                break;
        }
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cdGanhoStr = request.getParameter("cdGanho");
        if (cdGanhoStr != null && !cdGanhoStr.isEmpty()) {
            try {
                int cdGanho = Integer.parseInt(cdGanhoStr);
                dao.remover(cdGanho);
                request.setAttribute("msg", "Ganho removido!");
            } catch (NumberFormatException | DBException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao excluir.");
            }
        } else {
            request.setAttribute("erro", "Código de ganho não fornecido.");
        }
        listar(request, response);
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String descricao = request.getParameter("descricao");
            descricao = descricao.toUpperCase();
            String categoria = request.getParameter("categoria");
            double valor = Double.parseDouble(request.getParameter("valor"));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar dtRecebimento = Calendar.getInstance();
            dtRecebimento.setTime(format.parse(request.getParameter("dtRecebimento")));
            HttpSession session = request.getSession();
            String idUsuario = (String) session.getAttribute("user");

            Ganho ganho = new Ganho(0, descricao, categoria, valor, dtRecebimento, idUsuario);
            dao.cadastrar(ganho);

            request.setAttribute("msg", "Ganho cadastrado!");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao cadastrar.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados.");
        }

        request.getRequestDispatcher("cadastro-ganho.jsp").forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response, int cdGanho)
            throws ServletException, IOException {
        try {
            String descricao = request.getParameter("descricao");
            descricao = descricao.toUpperCase();
            String categoria = request.getParameter("categoria");
            double valor = Double.parseDouble(request.getParameter("valor"));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Calendar dtRecebimento = Calendar.getInstance();
            dtRecebimento.setTime(format.parse(request.getParameter("dtRecebimento")));
            HttpSession session = request.getSession();
            String idUsuario = (String) session.getAttribute("user");
            
            Ganho ganho = new Ganho(cdGanho, descricao, categoria, valor, dtRecebimento, idUsuario);
            dao.atualizar(ganho);

            request.setAttribute("msg", "Ganho atualizado!");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao atualizar.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados.");
        }

        listar(request, response);
    }
}

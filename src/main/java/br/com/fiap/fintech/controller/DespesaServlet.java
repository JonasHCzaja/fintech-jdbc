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

import br.com.fiap.fintech.bean.Despesa;
import br.com.fiap.fintech.dao.DespesaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;


@WebServlet("/despesa")
public class DespesaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DespesaDAO dao;
	
	@Override
	public void init() throws ServletException{
		super.init();
		dao = DAOFactory.getDespesaDAO();
	}
	
    public DespesaServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

        switch (acao) {
            case "listar":
                listar(request, response);
                break;
            case "abrir-form-edicao":
                String cdDespesaStr = request.getParameter("cdDespesa");
                if (cdDespesaStr != null && !cdDespesaStr.isEmpty()) {
                    try {
                        int cdDespesa = Integer.parseInt(cdDespesaStr);
                        Despesa despesa = dao.buscar(cdDespesa);
                        request.setAttribute("despesa", despesa);
                        request.getRequestDispatcher("edicao-despesa.jsp").forward(request, response);
                    } catch (NumberFormatException e) {
                        request.setAttribute("erro", "Código de despesa inválido.");
                        listar(request, response);
                    }
                } else {
                    request.setAttribute("erro", "Código de despesa não fornecido.");
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
        List<Despesa> lista = dao.listar(idUsuario);
        System.out.println("Número de Despesas: " + lista.size());
        request.setAttribute("despesas", lista);
        request.getRequestDispatcher("lista-despesa.jsp").forward(request, response);
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(request, response);
                break;
            case "editar":
                String cdDespesaStr = request.getParameter("cdDespesa");
                if (cdDespesaStr != null && !cdDespesaStr.isEmpty()) {
                    try {
                        int cdDespesa = Integer.parseInt(cdDespesaStr);
                        editar(request, response, cdDespesa);
                    } catch (NumberFormatException e) {
                        request.setAttribute("erro", "Código despesa inválido.");
                        listar(request, response);
                    }
                } else {
                    request.setAttribute("erro", "Código de despesa não fornecido.");
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

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String descricao = request.getParameter("descricao");
			descricao = descricao.toUpperCase();
			String categoria = request.getParameter("categoria");
			String tipoDespesa = request.getParameter("tipoDespesa");
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dtDespesa = Calendar.getInstance(); 
			dtDespesa.setTime(format.parse(request.getParameter("dtDespesa")));
			String dsCartao = request.getParameter("dsCartao");
			dsCartao = dsCartao.toUpperCase();
			HttpSession session = request.getSession();
            String idUsuario = (String) session.getAttribute("user");
			
			Despesa despesa;
			despesa = new Despesa(0, tipoDespesa, descricao, categoria, valor, dtDespesa, dsCartao, idUsuario);
			
			
			dao.cadastrar(despesa);
			
			request.setAttribute("msg", "Despesa cadastrada!");
		} catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Pro favor, valide os dados");
		}
		
		request.getRequestDispatcher("cadastro-despesa.jsp").forward(request, response);
	}

	
	private void editar(HttpServletRequest request, HttpServletResponse response, int cdDespesa)
            throws ServletException, IOException {
        try {
        	String descricao = request.getParameter("descricao");
        	descricao = descricao.toUpperCase();
			String categoria = request.getParameter("categoria");
			String tipoDespesa = request.getParameter("tipoDespesa");
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dtDespesa = Calendar.getInstance(); 
			dtDespesa.setTime(format.parse(request.getParameter("dtDespesa")));
			String dsCartao = request.getParameter("dsCartao");
			dsCartao = dsCartao.toUpperCase();
			HttpSession session = request.getSession();
            String idUsuario = (String) session.getAttribute("user");
			
			Despesa despesa;
			despesa = new Despesa(cdDespesa, tipoDespesa, descricao, categoria, valor, dtDespesa, dsCartao, idUsuario);
			
			
			dao.atualizar(despesa);
			
			request.setAttribute("msg", "Despesa atualizada!");
        } catch (DBException db) {
            db.printStackTrace();
            request.setAttribute("erro", "Erro ao atualizar.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Por favor, valide os dados.");
        }

        listar(request, response);
    }
	
	
	private void excluir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cdDespesaStr = request.getParameter("cdDespesa");
        if (cdDespesaStr != null && !cdDespesaStr.isEmpty()) {
            try {
                int cdDespesa = Integer.parseInt(cdDespesaStr);
                dao.remover(cdDespesa);
                request.setAttribute("msg", "Despesa removida!");
            } catch (NumberFormatException | DBException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao excluir.");
            }
        } else {
            request.setAttribute("erro", "Código de despesa não fornecido.");
        }
        listar(request, response);
    }
	
	
	
}

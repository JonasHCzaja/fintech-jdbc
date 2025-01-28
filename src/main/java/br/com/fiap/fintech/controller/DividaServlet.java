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

import br.com.fiap.fintech.bean.Divida;
import br.com.fiap.fintech.dao.DividaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;


@WebServlet("/divida")
public class DividaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private DividaDAO dao;
    
    @Override
    public void init() throws ServletException{
    	super.init();
    	dao = DAOFactory.getDividaDAO();
    }
	
    public DividaServlet() {
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
                String cdDividaStr = request.getParameter("cdDivida");
                if (cdDividaStr != null && !cdDividaStr.isEmpty()) {
                    try {
                        int cdDivida = Integer.parseInt(cdDividaStr);
                        Divida divida = dao.buscar(cdDivida);
                        request.setAttribute("divida", divida);
                        request.getRequestDispatcher("edicao-divida.jsp").forward(request, response);
                    } catch (NumberFormatException e) {
                        request.setAttribute("erro", "Código de divida inválido.");
                        listar(request, response);
                    }
                } else {
                    request.setAttribute("erro", "Código de divida não fornecido.");
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
        List<Divida> lista = dao.listar(idUsuario);
        System.out.println("Número de dividas: " + lista.size());
        request.setAttribute("dividas", lista);
        request.getRequestDispatcher("lista-divida.jsp").forward(request, response);
    }
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(request, response);
                break;
            case "editar":
                String cdDividaStr = request.getParameter("cdDivida");
                if (cdDividaStr != null && !cdDividaStr.isEmpty()) {
                    try {
                        int cdDivida = Integer.parseInt(cdDividaStr);
                        editar(request, response, cdDivida);
                    } catch (NumberFormatException e) {
                        request.setAttribute("erro", "Código de divida inválido.");
                        listar(request, response);
                    }
                } else {
                    request.setAttribute("erro", "Código de divida não fornecido.");
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
			double valor = Double.parseDouble(request.getParameter("valor"));
			String descricao = request.getParameter("descricao");
			descricao = descricao.toUpperCase();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar prazo = Calendar.getInstance(); 
			prazo.setTime(format.parse(request.getParameter("prazo")));
			HttpSession session = request.getSession();
            String idUsuario = (String) session.getAttribute("user");
			
			Divida divida = new Divida(0, valor, descricao, prazo, idUsuario);
			dao.cadastrar(divida);			
			
			request.setAttribute("msg", "Divida cadastrada!");
		} catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Pro favor, valide os dados");
		}
		
		request.getRequestDispatcher("cadastro-divida.jsp").forward(request, response);
	}

	
	private void editar(HttpServletRequest request, HttpServletResponse response, int cdDivida)
            throws ServletException, IOException {
		try {
			double valor = Double.parseDouble(request.getParameter("valor"));
			String descricao = request.getParameter("descricao");
			descricao = descricao.toUpperCase();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar prazo = Calendar.getInstance(); 
			prazo.setTime(format.parse(request.getParameter("prazo")));
			HttpSession session = request.getSession();
            String idUsuario = (String) session.getAttribute("user");
			
			Divida divida = new Divida(cdDivida, valor, descricao, prazo, idUsuario);
			dao.atualizar(divida);			
			
			request.setAttribute("msg", "Divida atualizada!");
		} catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Pro favor, valide os dados");
		}
		
		listar(request, response);
	}
	
	private void excluir (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cdDividaStr = request.getParameter("cdDivida");
        if (cdDividaStr != null && !cdDividaStr.isEmpty()) {
            try {
                int cdDivida = Integer.parseInt(cdDividaStr);
                dao.remover(cdDivida);
                request.setAttribute("msg", "Divida removida!");
            } catch (NumberFormatException | DBException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao excluir.");
            }
        } else {
            request.setAttribute("erro", "Código da divida não fornecido.");
        }
        listar(request, response);
    }
	
	
}

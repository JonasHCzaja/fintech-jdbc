package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.fintech.bean.Investimento;
import br.com.fiap.fintech.dao.InvestimentoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;


@WebServlet("/investimento")
public class InvestimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private InvestimentoDAO dao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOFactory.getInvestimentoDAO();
	}
	
	
    public InvestimentoServlet() {
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
            String cdAtivoStr = request.getParameter("cdAtivo");
            if (cdAtivoStr != null && !cdAtivoStr.isEmpty()) {
                try {
                    int cdAtivo = Integer.parseInt(cdAtivoStr);
                    Investimento investimento = dao.buscar(cdAtivo);
                    request.setAttribute("investimento", investimento);
                    request.getRequestDispatcher("edicao-investimento.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    request.setAttribute("erro", "Código de investimento inválido.");
                    listar(request, response);
                }
            } else {
                request.setAttribute("erro", "Código de investimento não fornecido.");
                listar(request, response);
            }
            break;
        default:
            listar(request, response);
            break;
    	}
    	
	}


	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        String idUsuario = (String) session.getAttribute("user");
		List<Investimento> lista = dao.listar(idUsuario);
        System.out.println("Número de investimentos: " + lista.size());
		request.setAttribute("investimentos", lista);
		request.getRequestDispatcher("lista-investimento.jsp").forward(request, response);
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
            String cdAtivoStr = request.getParameter("cdAtivo");
            if (cdAtivoStr != null && !cdAtivoStr.isEmpty()) {
                try {
                    int cdAtivo = Integer.parseInt(cdAtivoStr);
                    editar(request, response, cdAtivo);
                } catch (NumberFormatException e) {
                    request.setAttribute("erro", "Código de investimento inválido.");
                    listar(request, response);
                }
            } else {
                request.setAttribute("erro", "Código de investimento não fornecido.");
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
			String nomeAtivo = request.getParameter("nomeAtivo");
			nomeAtivo = nomeAtivo.toUpperCase();
			String categoriaAtivo = request.getParameter("categoriaAtivo");
			double precoMedio = Double.parseDouble(request.getParameter("precoMedio"));
			double quantidade = Double.parseDouble(request.getParameter("quantidade"));
			HttpSession session = request.getSession();
            String idUsuario = (String) session.getAttribute("user");
			
			Investimento investimento = new Investimento(0, nomeAtivo, categoriaAtivo, precoMedio, quantidade, idUsuario);
			dao.cadastrar(investimento);
			
			request.setAttribute("msg", "Investimento cadastrado!");
		} catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Pro favor, valide os dados");
		}
		
		request.getRequestDispatcher("cadastro-investimento.jsp").forward(request, response);
	}

	
	private void editar(HttpServletRequest request, HttpServletResponse response, int cdAtivo)
            throws ServletException, IOException {
        try {
        	String nomeAtivo = request.getParameter("nomeAtivo");
        	nomeAtivo = nomeAtivo.toUpperCase();
			String categoriaAtivo = request.getParameter("categoriaAtivo");
			double precoMedio = Double.parseDouble(request.getParameter("precoMedio"));
			double quantidade = Double.parseDouble(request.getParameter("quantidade"));
			HttpSession session = request.getSession();
            String idUsuario = (String) session.getAttribute("user");
			
			Investimento investimento = new Investimento(cdAtivo, nomeAtivo, categoriaAtivo, precoMedio, quantidade, idUsuario);
			dao.atualizar(investimento);
			
			request.setAttribute("msg", "Investimento atualizado!");
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
        String cdAtivoStr = request.getParameter("cdAtivo");
        if (cdAtivoStr != null && !cdAtivoStr.isEmpty()) {
            try {
                int cdAtivo = Integer.parseInt(cdAtivoStr);
                dao.remover(cdAtivo);
                request.setAttribute("msg", "Investimento removido!");
            } catch (NumberFormatException | DBException e) {
                e.printStackTrace();
                request.setAttribute("erro", "Erro ao excluir.");
            }
        } else {
            request.setAttribute("erro", "Código de investimento não fornecido.");
        }
        listar(request, response);
    }
	
}

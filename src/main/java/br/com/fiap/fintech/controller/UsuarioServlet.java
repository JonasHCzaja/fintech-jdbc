package br.com.fiap.fintech.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;


@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private UsuarioDAO dao;
	
    @Override
    public void init() throws ServletException {
    	super.init();
    	dao = DAOFactory.getUsuarioDAO();
    }
    
    public UsuarioServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "logout":
			logout(request, response);
			break;
		
		case "abrir-form-edicao":
		
		String idUsuario = request.getParameter("idUsuario");
		Usuario usuario = dao.buscar(idUsuario);
		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher("edicao-usuario.jsp").forward(request, response);
		break;
		default :
			request.getRequestDispatcher("home.jsp").forward(request, response);
			break;
		}
		
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "editar":
			String idUsuario = request.getParameter("idUsuario");
			editar(request, response, idUsuario);
			break;
		
		}
		
	
	
	
	
	
	}

	 private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String email = request.getParameter("email");
	        String senha = request.getParameter("senha");
	        
	        Usuario usuario = new Usuario();
	        usuario.setEmail(email);
	        usuario.setSenha(senha);
	        
	        if (dao.validarUsuario(usuario)) {
	            HttpSession session = request.getSession();
	            session.setAttribute("user", email);
	            request.getRequestDispatcher("home.jsp").forward(request, response); 
	        } else {
	            request.setAttribute("erro", "Usuário e/ou senha inválidos");
	            request.getRequestDispatcher("home.jsp").forward(request, response);
	        }
	    }

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String email = request.getParameter("email");
			String nome = request.getParameter("nome");
			String senha = request.getParameter("senha");
			
			Usuario usuario = new Usuario(email, nome, senha);
			dao.cadastrar(usuario);
			
			request.setAttribute("msg", "Usuario cadastrado!");
		} catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Pro favor, valide os dados");
		}
		
		request.getRequestDispatcher("menu.jsp").forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response, String idUsuario)
			throws ServletException, IOException {
		try {
			String email = request.getParameter("idUsuario");
			String nome = request.getParameter("nome");
			String senha = request.getParameter("senha");
			
			Usuario usuario = new Usuario(email, nome, senha);
			dao.atualizarDados(usuario);
			
			request.setAttribute("msg", "Usuario atualizado!");
		} catch(DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Pro favor, valide os dados");
		}
		
		request.getRequestDispatcher("menu.jsp").forward(request, response);
	}
	
	
	
	
	
	
	
}

package br.com.fiap.fintech.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Filtro responsável por interceptar todas as requisições e verificar
 * se o usuário está autenticado. Caso contrário, redireciona para a página de login.
 */

@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {
     
    private static final long serialVersionUID = 1L;

    public LoginFilter() {
        super();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    /**
     * Método principal do filtro, responsável por processar cada requisição.
     * Verifica se o usuário está autenticado e, caso contrário, redireciona para a página inicial.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false); // Obtém a sessão atual (sem criar uma nova).
        String url = req.getRequestURI(); // Obtém a URL da requisição.

        // Verifica se a sessão é inválida ou o usuário não está autenticado,
        // e permite acesso a URLs específicas sem autenticação.
        if ((session == null || session.getAttribute("user") == null) 
                && !url.endsWith("usuario") // Permite acesso a URLs que terminam com "usuario".
                && !url.contains("resources") // Permite acesso a recursos estáticos.
                && !url.contains("home") // Permite acesso à página inicial.
                && !url.contains("cadastro-usuario.jsp")) { // Permite acesso à página de cadastro.
            request.setAttribute("erro", "Entre com o usuario e senha!"); // Define mensagem de erro.
            request.getRequestDispatcher("home.jsp").forward(request, response); // Redireciona para a página inicial.
        } else {
            // Continua o processamento da requisição caso o usuário esteja autenticado ou a URL seja permitida.
            chain.doFilter(request, response);
        }
    }


    /**
     * Método de destruição do filtro (opcional).
     * Pode ser utilizado para liberar recursos.
     */
    @Override
    public void destroy() {
        
    }
}

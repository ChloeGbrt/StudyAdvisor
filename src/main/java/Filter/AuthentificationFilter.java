package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthentificationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("test");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String Pseudo = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");
		System.out.println(Pseudo);
		if (Pseudo == null || "".equals(Pseudo)) {
			System.out.println("Il faut être connecté pour accéder à cette page !");
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("../StudyAdvisor/Accueil");
			System.out.println(Pseudo);
			return;
			
		}
		chain.doFilter(request, response);
	}

	

	@Override
	public void destroy() {

	}

}

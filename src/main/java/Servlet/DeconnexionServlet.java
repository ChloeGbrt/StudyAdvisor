package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Deconnexion")
public class DeconnexionServlet extends HttpServlet {

	private static final long serialVersionUID = 790144859160282054L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("utilisateurConnecte");
		resp.sendRedirect("Accueil");
	}

	
}

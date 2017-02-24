package Servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import utils.MotDePasseUtils;



@WebServlet("/Accueil")
public class AccueilServlet extends AbstractGenericServlet {
	
	private static final long serialVersionUID = -1488650966375438002L;

	private Map<String, String> utilisateursAutorises;
	@Override
	public void init() throws ServletException {
		utilisateursAutorises = new HashMap<>();
		
		utilisateursAutorises.put("Admin", "5b079a43866dcd674f6a0b703d44e284c88af94e6382ef60:c4564991a274a12d76cfe67375017d9c7e48e2b6b1d6f438");
		utilisateursAutorises.put("Alex", "4db17c9033bfe5ea1a1f1040d7f391ed5bcc8ae2662ccf02:281d116151bc517f0fbc96fb794f7cb0eaedea7c53e3cc65");

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		templateEngine.process("Accueil", context, resp.getWriter());
		
}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String loginSaisi = req.getParameter("Pseudo");
		String passwordSaisi = req.getParameter("MotDePass");
	
			try {
				if(utilisateursAutorises.containsKey(loginSaisi) 
						&& MotDePasseUtils.validerMotDePasse(passwordSaisi, utilisateursAutorises.get(loginSaisi))) {
					req.getSession().setAttribute("utilisateurConnecte", loginSaisi);
					resp.sendRedirect("Metiers");
					
				} else {
					resp.sendRedirect("Accueil");
				}
				
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
			} 
		
	}


	
}

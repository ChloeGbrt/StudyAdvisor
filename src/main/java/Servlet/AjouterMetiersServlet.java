package Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


import entities.Metier;
import managers.MetierLibrary;



@WebServlet("/AjouterMetiers")
public class AjouterMetiersServlet extends AbstractGenericServlet {

	
	private static final long serialVersionUID = 4982865059712541281L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		List<Metier> Metiers = MetierLibrary.getInstance().listMetiers();
		context.setVariable("Metier", Metiers);
		
		templateEngine.process("AjouterMetiers", context, resp.getWriter());
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String Nom = req.getParameter("Nom");
		String Description =req.getParameter("Description");
			
	
		System.out.println("Nom" + Nom);
        System.out.println("Description" + Description);

			
        Metier newMetier = new Metier(null, Nom, Description);
        MetierLibrary.getInstance().addMetier(newMetier);
        
        resp.sendRedirect("Metiers");
	}

}
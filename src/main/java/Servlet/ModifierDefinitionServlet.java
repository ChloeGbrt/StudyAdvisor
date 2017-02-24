package Servlet;

import java.io.IOException;
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



@WebServlet("/ModifierDefinition")
public class ModifierDefinitionServlet extends AbstractGenericServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		
		Integer idMetier = Integer.parseInt(req.getParameter("idMetier"));
		Metier Metiers = MetierLibrary.getInstance().getMetier(idMetier);
		context.setVariable("Metier", Metiers);
		
		templateEngine.process("ModifierDefinition", context, resp.getWriter());
		
}
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer idMetier= Integer.parseInt(req.getParameter("idMetier"));
		String Nom = req.getParameter("Nom");
		String Description =req.getParameter("Description");
			
	
		System.out.println("Nom" + Nom);
        System.out.println("Description" + Description);

			
        Metier newMetier = new Metier(null, Nom, Description);
        MetierLibrary.getInstance().add(newMetier);
        
        resp.sendRedirect("Metiers");
        
 
	}
	
}
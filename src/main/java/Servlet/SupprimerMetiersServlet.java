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

import dao.impl.MetierDaoImpl;
import entities.Metier;
import managers.MetierLibrary;



@WebServlet("/Supprimer")
public class SupprimerMetiersServlet extends AbstractGenericServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		TemplateEngine templateEngine = this.createTemplateEngine(req);
		
		WebContext context = new WebContext(req, resp, req.getServletContext());
		List<Metier> Metiers = MetierLibrary.getInstance().listMetiers();
		context.setVariable("Metier", Metiers);
		
		templateEngine.process("Supprimer", context, resp.getWriter());
		
}
	
		


  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	Integer idMetier= Integer.parseInt(req.getParameter("idMetier"));
	String Nom = req.getParameter("Nom");
	String Description =req.getParameter("Description");
	
	MetierDaoImpl MetierDao = new MetierDaoImpl();
	MetierDao.deleteMetier(idMetier);
		

	System.out.println("Nom" + Nom);
    System.out.println("Description" + Description);
    System.out.println("idMetier:" + idMetier);
    
    resp.sendRedirect("Metiers");
	
	  

}

}
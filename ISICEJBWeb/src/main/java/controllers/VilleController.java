package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.VilleIDao;
import entities.Ville;

/**
 * Servlet implementation class VilleController
 */

public class VilleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private VilleIDao ejb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VilleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("villes", ejb.findAll());
	    RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");	
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if ("delete".equals(action)) {
		    
			int VilleId = Integer.parseInt(request.getParameter("id"));
		    
			//System.out.println("test" + ejb.findById(VilleId).getId());
		    
		    if(ejb.findById(VilleId)!= null) {
		    	
		    	Ville Villedelete =  ejb.findById(VilleId);		        
		    	System.out.println("test delete");
		        ejb.delete(Villedelete);
		        response.sendRedirect(request.getContextPath() + "/ville.jsp");		        
		    }
		    
		} else if("update".equals(action)) {
	        
			int id = Integer.parseInt(request.getParameter("id"));
	        String nom = request.getParameter("villeNom");
			
	        Ville VilleToEdit = ejb.findById(id);
	        
	        if (VilleToEdit != null) {
	        	VilleToEdit.setNom(nom);	      
	            ejb.update(VilleToEdit);
	        }
	        
	        response.sendRedirect(request.getContextPath() + "/ville.jsp");
	    }
		
		else if("add".equals(action)) {
			
			String nom = request.getParameter("villeNom");
			System.out.println("test add");
			
			Ville newVille = new Ville();
			newVille.setNom(nom);
			
			ejb.create(newVille);
			
			response.sendRedirect(request.getContextPath() + "/VilleController");
		} 
	}

}

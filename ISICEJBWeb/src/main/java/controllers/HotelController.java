package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.HotelIDao;

import entities.Hotel;
import entities.Hotel;

/**
 * Servlet implementation class HotelController
 */

public class HotelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private HotelIDao ejb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HotelController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("hotels", ejb.findAll());
		RequestDispatcher dispatcher = request.getRequestDispatcher("hotel.jsp");

		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("delete".equals(action)) {

			int HotelId = Integer.parseInt(request.getParameter("id"));

			// System.out.println("test" + ejb.findById(HotelId).getId());

			if (ejb.findById(HotelId) != null) {

				Hotel Hoteldelete = ejb.findById(HotelId);
				System.out.println("test delete");
				ejb.delete(Hoteldelete);
				response.sendRedirect(request.getContextPath() + "/hotel.jsp");
			}

		} else if ("update".equals(action)) {

			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");

			Hotel HotelToEdit = ejb.findById(id);

			if (HotelToEdit != null) {
				HotelToEdit.setNom(nom);
				ejb.update(HotelToEdit);
			}

			response.sendRedirect(request.getContextPath() + "/hotel.jsp");
		}

		else if ("add".equals(action)) {

			System.out.println("test add");

			String nom = request.getParameter("nom");
			String adresse = request.getParameter("adresse");
			String telephone = request.getParameter("telephone");
			ejb.create(new Hotel(nom, adresse, telephone));


			response.sendRedirect(request.getContextPath() + "/HotelController");
		}
	}
}

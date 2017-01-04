package fr.pizzeria.admin.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientServiceEJB;

/**
 * Servlet implementation class RemoveClientController
 */
@WebServlet("/clients/delete")
public class RemoveClientController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3792140738638386740L;

	@EJB
	private ClientServiceEJB clientServiceEJB;


	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		clientServiceEJB.deleteClient(id);
		response.getWriter().println("Client supprimée");
		response.setStatus(200);
	}

}

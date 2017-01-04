package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientServiceEJB;
import fr.pizzeria.model.Client;

/**
 * Servlet implementation class ListClientController
 */
@WebServlet("/clients/list")
public class ListClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ClientServiceEJB clientServiceEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Client> clients = clientServiceEJB.findAllClients();
		request.setAttribute("ListClients", clients);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/clients/listClients.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

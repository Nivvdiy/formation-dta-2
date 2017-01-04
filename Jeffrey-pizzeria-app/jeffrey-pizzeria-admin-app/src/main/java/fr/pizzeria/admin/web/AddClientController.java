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
 * Servlet implementation class UpdateClientController
 */
@WebServlet("/clients/new")
public class AddClientController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3802933391766510822L;

	@EJB
	private ClientServiceEJB clientServiceEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/clients/addClient.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Client> clients = clientServiceEJB.findAllClients();
		boolean error=false;
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String ville = request.getParameter("ville");
		int age = Integer.parseInt( request.getParameter("age"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		for(Client c : clients){
			if (c.getEmail().equals(email)) {
				error=true;
			}
		}
		if(!error){
			clientServiceEJB.saveNewClient(new Client(nom, prenom, ville, age, email, password));
			response.getWriter().println("Client ajouter");
		} else {
			response.getWriter().println("Client déjà existant");
		}
		response.setStatus(200);
	}

}

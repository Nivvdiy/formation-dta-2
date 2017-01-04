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
@WebServlet("/clients/edit")
public class UpdateClientController extends HttpServlet {
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
		List<Client> clients = clientServiceEJB.findAllClients();
		clients.forEach(p -> {
			if(p.getId()==Integer.parseInt(request.getParameter("id"))){
				request.setAttribute("ModifiedClient", p);
			}
		});
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/clients/updateClient.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Client> clients = clientServiceEJB.findAllClients();
		int id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String ville = request.getParameter("ville");
		int age = Integer.parseInt( request.getParameter("age"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		clients.forEach(p -> {
			if (p.getId()==id) {
				p.setNom(nom);
				p.setPrenom(prenom);
				p.setVille(ville);
				p.setAge(age);
				p.setEmail(email);
				p.setPassword(password);
				clientServiceEJB.updateClient(id, p);
			}
		});
		response.getWriter().println("Client mise à jour");
		response.setStatus(200);
	}

}

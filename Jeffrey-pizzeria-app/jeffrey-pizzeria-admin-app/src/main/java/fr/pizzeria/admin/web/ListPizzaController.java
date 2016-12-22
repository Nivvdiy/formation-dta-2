package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.PizzaServiceEJB;
import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.model.Pizza;

/**
 * Servlet implementation class ListPizzaController
 */
@WebServlet("/pizzas/list")
public class ListPizzaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private DaoFactory jpaDao;
	@EJB
	private PizzaServiceEJB pizzaServiceEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pizza> pizzas = pizzaServiceEJB.findAllPizzas();
		request.setAttribute("ListPizzas", pizzas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/pizzas/listPizzas.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}

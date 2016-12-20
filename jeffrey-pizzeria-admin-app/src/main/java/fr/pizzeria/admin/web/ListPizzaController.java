package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.JPADaoFactory;
import fr.pizzeria.model.Pizza;

/**
 * Servlet implementation class ListPizzaController
 */
public class ListPizzaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DaoFactory jpaDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListPizzaController() {
		try {
			jpaDao = new JPADaoFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Pizza> pizzas = jpaDao.getPizzaDao().findAllPizzas();
		request.setAttribute("ListPizzas", pizzas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/pizzas/listPizzas.jsp");
		dispatcher.forward(request, response);
	}

}

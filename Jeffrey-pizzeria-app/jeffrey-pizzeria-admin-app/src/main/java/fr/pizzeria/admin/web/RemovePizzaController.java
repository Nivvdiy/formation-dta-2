package fr.pizzeria.admin.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.PizzaServiceEJB;
import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.JPADaoFactory;

/**
 * Servlet implementation class RemovePizzaController
 */
@WebServlet("/pizzas/delete")
public class RemovePizzaController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3792140738638386740L;

	@Inject
	private DaoFactory jpaDao;
	@EJB
	private PizzaServiceEJB pizzaServiceEJB;


	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		pizzaServiceEJB.deletePizza(code);
		response.getWriter().println("Pizza supprimée");
		response.setStatus(200);
	}

}

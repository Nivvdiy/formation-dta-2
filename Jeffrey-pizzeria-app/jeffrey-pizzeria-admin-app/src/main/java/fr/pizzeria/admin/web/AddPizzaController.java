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
import fr.pizzeria.model.Pizza.Category;

/**
 * Servlet implementation class UpdatePizzaController
 */
@WebServlet("/pizzas/new")
public class AddPizzaController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3802933391766510822L;

	@Inject
	private DaoFactory jpaDao;
	@EJB
	private PizzaServiceEJB pizzaServiceEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/pizzas/addPizza.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pizza> pizzas = pizzaServiceEJB.findAllPizzas();
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		Category cat = Category.parseCategory(request.getParameter("category"));
		String image = request.getParameter("image").substring(request.getParameter("image").lastIndexOf('\\')+1);
		boolean error=false;
		for(Pizza p : pizzas){
			if (p.getCode()==code) {
				error=true;
			}
		}
		if(!error){
			pizzaServiceEJB.saveNewPizza(new Pizza(code, name, price, cat, image, true));
		}
		response.getWriter().println("Pizza ajouter");
		response.setStatus(200);
	}

}

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

import fr.pizzeria.admin.metier.PizzaServiceEJB;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

/**
 * Servlet implementation class UpdatePizzaController
 */
@WebServlet("/pizzas/edit")
public class UpdatePizzaController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3802933391766510822L;

	@EJB
	private PizzaServiceEJB pizzaServiceEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pizza> pizzas = pizzaServiceEJB.findAllPizzas();
		pizzas.forEach(p -> {
			if(p.getCode().equals(request.getParameter("code"))){
				request.setAttribute("ModifiedPizza", p);
			}
		});
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/pizzas/updatePizza.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pizza> pizzas = pizzaServiceEJB.findAllPizzas();
		int id = Integer.parseInt(request.getParameter("id"));
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		Category cat = Category.parseCategory(request.getParameter("category"));
		String image = request.getParameter("image").substring(request.getParameter("image").lastIndexOf('\\')+1);
		pizzas.forEach(p -> {
			if (p.getId()==id) {
				p.setCategory(cat);
				p.setCode(code);
				p.setImage(image);
				p.setName(name);
				p.setPrice(price);
				pizzaServiceEJB.updatePizza(code, p);
			}
		});
		response.getWriter().println("Pizza mise à jour");
		response.setStatus(200);
	}

}

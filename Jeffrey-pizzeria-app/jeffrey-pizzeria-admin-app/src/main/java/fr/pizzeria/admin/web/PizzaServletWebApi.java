package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.PizzaServiceEJB;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

/**
 * Servlet implementation class PizzaServletWebApi
 */
@WebServlet("/api/servlet/pizzas")
public class PizzaServletWebApi extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2391966807121887085L;

	@EJB
	private PizzaServiceEJB pizzaServiceEJB;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pizza> pizzas = pizzaServiceEJB.findAllPizzas();
		if(Pizza.getNbPizza()>0){
			pizzas.forEach((pizza)->{
				try {
					response.getWriter().println(pizza.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} else {
			response.getWriter().println("La table pizza est vide\n");
		}
		response.setStatus(200);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		Category cat = Category.parseCategory(request.getParameter("category"));
		String image = request.getParameter("image");
		pizzaServiceEJB.saveNewPizza(new Pizza(code, name, price, cat, image, true));
		response.getWriter().println("Pizza enregistrée");
		response.setStatus(201);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Pizza> pizzas = pizzaServiceEJB.findAllPizzas();
		String code = request.getParameter("code");
		String newCode = request.getParameter("code");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		Category cat = Category.parseCategory(request.getParameter("category"));
		String image = request.getParameter("image");
		pizzas.forEach(p -> {
			if (p.getCode().equals(code)) {
				p.setCategory(cat);
				p.setCode(newCode);
				p.setImage(image);
				p.setName(name);
				p.setPrice(price);
				pizzaServiceEJB.updatePizza(code, p);
			}
		});
		response.getWriter().println("Pizza mise à jour");
		response.setStatus(200);
	}

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

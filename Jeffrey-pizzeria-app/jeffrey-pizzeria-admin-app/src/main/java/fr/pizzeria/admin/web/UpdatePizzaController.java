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
import fr.pizzeria.model.Pizza.Category;

/**
 * Servlet implementation class UpdatePizzaController
 */
public class UpdatePizzaController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3802933391766510822L;
	
	DaoFactory jpaDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePizzaController() {
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
		List<Pizza> pizzas = jpaDao.getPizzaDao().findAllPizzas();
		int id = Integer.parseInt(request.getParameter("id"));
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		Category cat = Category.parseCategory(request.getParameter("category"));
		String image = request.getParameter("image");
		pizzas.forEach(p -> {
			if (p.getId()==id) {
				p.setCategory(cat);
				p.setCode(code);
				p.setImage(image);
				p.setName(name);
				p.setPrice(price);
				jpaDao.getPizzaDao().updatePizza(code, p);
			}
		});
		response.getWriter().println("Pizza mise à jour");
		response.setStatus(200);
	}

}

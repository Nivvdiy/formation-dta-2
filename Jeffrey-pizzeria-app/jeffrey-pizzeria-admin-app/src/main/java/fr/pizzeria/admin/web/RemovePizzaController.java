package fr.pizzeria.admin.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.JPADaoFactory;

/**
 * Servlet implementation class RemovePizzaController
 */
public class RemovePizzaController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3792140738638386740L;
	
	DaoFactory jpaDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemovePizzaController() {
		try {
			jpaDao = new JPADaoFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		jpaDao.getPizzaDao().deletePizza(code);
		response.getWriter().println("Pizza supprimée");
		response.setStatus(200);
	}

}

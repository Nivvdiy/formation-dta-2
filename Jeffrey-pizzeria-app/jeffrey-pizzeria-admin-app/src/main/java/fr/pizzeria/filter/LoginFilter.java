package fr.pizzeria.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/pizzas/*" }, description = "Authentification")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) req).getSession();
		if("admin@pizzeria.fr".equals(session.getAttribute("email")) && "admin".equals(session.getAttribute("password"))){
			chain.doFilter(req, res);
		} else {
			((HttpServletResponse) res).sendRedirect("/jeffrey-pizzeria-admin-app/login");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}

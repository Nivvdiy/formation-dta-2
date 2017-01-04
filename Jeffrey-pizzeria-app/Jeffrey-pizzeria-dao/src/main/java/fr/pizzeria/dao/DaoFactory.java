package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.dao.pizzadao.PizzaDao;

@Component
public class DaoFactory {

	PizzaDao pizzaDao;

	@Autowired
	public DaoFactory(PizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}

	public PizzaDao getPizzaDao() {
		return this.pizzaDao;
	}
}

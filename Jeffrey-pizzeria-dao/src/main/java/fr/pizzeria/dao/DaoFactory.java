package fr.pizzeria.dao;

import fr.pizzeria.dao.pizzadao.PizzaDao;

public abstract class DaoFactory {

	PizzaDao pizzaDao;

	public DaoFactory(PizzaDao pizzaDao) {
		super();
		this.pizzaDao = pizzaDao;
	}

	public PizzaDao getPizzaDao() {
		return this.pizzaDao;
	}
}

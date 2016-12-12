package fr.pizzeria.dao;

import fr.pizzeria.dao.pizzadao.PizzaDaoJPA;

public class JPADaoFactory extends DaoFactory {

	public JPADaoFactory() throws Exception {
		super(new PizzaDaoJPA());
	}

}

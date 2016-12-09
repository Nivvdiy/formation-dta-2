package fr.pizzeria.dao;

import fr.pizzeria.dao.pizzadao.PizzaDaoMySQL;

public class MySQLDaoFactory extends DaoFactory {

	public MySQLDaoFactory() throws Exception {
		super(new PizzaDaoMySQL());
	}

}

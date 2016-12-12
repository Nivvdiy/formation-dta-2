package fr.pizzeria.dao;

import fr.pizzeria.dao.pizzadao.PizzaDaoArray;

public class ArrayDaoFactory extends DaoFactory {

	public ArrayDaoFactory() {
		super(new PizzaDaoArray());
	}
}

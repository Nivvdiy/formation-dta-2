package fr.pizzeria.dao.tab;

import fr.pizzeria.dao.IPizzaDao;

public class PizzaDaoTab extends IPizzaDao {

	public PizzaDaoTab() {
		super(new TabWR());
	}

}
package fr.pizzeria.dao.pizzadao;

import fr.pizzeria.dao.wrdao.TabWR;

public class PizzaDaoTab extends IPizzaDao {

	public PizzaDaoTab() {
		super(new TabWR());
	}

}
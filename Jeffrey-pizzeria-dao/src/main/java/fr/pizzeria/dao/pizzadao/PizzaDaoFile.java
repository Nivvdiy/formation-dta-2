package fr.pizzeria.dao.pizzadao;

import fr.pizzeria.dao.wrdao.FileWR;

public class PizzaDaoFile extends IPizzaDao {

	public PizzaDaoFile(String file) {
		super(new FileWR(file));
	}

}
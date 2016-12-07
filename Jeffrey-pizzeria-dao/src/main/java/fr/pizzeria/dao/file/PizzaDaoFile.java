package fr.pizzeria.dao.file;

import fr.pizzeria.dao.IPizzaDao;

public class PizzaDaoFile extends IPizzaDao {

	public PizzaDaoFile(String file) {
		super(new FileWR(file));
	}

}
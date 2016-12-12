package fr.pizzeria.dao;

import fr.pizzeria.dao.pizzadao.PizzaDaoFile;

public class FileDaoFactory extends DaoFactory {

	public FileDaoFactory(){
		super(new PizzaDaoFile());
	}

}

package fr.pizzeria.admin.metier;

import javax.enterprise.inject.Produces;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.JPADaoFactory;

public class PizzaService {

	@Produces
	public DaoFactory getDao(){
		try {
			return new JPADaoFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

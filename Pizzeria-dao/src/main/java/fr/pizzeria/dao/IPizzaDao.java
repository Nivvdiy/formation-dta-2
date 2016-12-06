package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.pizzeria.exception.LoadMemoriePizzaException;
import fr.pizzeria.exception.RemovePizzaException;
import fr.pizzeria.exception.SaveMemoriePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public abstract class IPizzaDao {

	private List<Pizza> pizzas = new ArrayList<>();
	private static Logger logger = Logger.getAnonymousLogger();

	private IWRDao iWRDao;

	public IPizzaDao(IWRDao iWRDao) {
		this.iWRDao = iWRDao;
	}

	public void loadPizzas() {
		try {
			iWRDao.read();
			pizzas = iWRDao.toPizzaList();
		} catch (LoadMemoriePizzaException e) {
			logger.log(Level.WARNING, e, null);
		}
	}

	public void savePizzas() {
		try {
			iWRDao.write();
		} catch (SaveMemoriePizzaException e) {
			logger.log(Level.WARNING, e, null);
		}
	}

	public List<Pizza> findAllPizzas() {
		return pizzas;
	}

	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		if (!isValid(pizza)) {
			throw new SavePizzaException(pizza);
		}
		pizzas.add(pizza);
	}

	public void updatePizza(int codePizza, Pizza pizza) throws UpdatePizzaException {
		if (!isValid(pizza) && !isValid(codePizza)) {
			throw new UpdatePizzaException(codePizza, pizza, getNbPizza());
		}
		pizzas.set(codePizza - 1, pizza);
	}

	public void removePizza(int codePizza) throws RemovePizzaException {
		if (!isValid(codePizza)) {
			throw new RemovePizzaException(codePizza, getNbPizza());
		}
		pizzas.remove(codePizza - 1);
		Pizza.removePizza();
	}

	private boolean isValid(int codePizza) {
		return (0 < codePizza) & (codePizza < pizzas.size() + 1);
	}

	private boolean isValid(Pizza pizza) {
		return pizza != null | pizza.getCode().length() == 3 & pizza.getName().length() > 0 & pizza.getPrice() > 0;
	}

	public int getNbPizza() {
		return Pizza.getNbPizza();
	}

	public IWRDao getIWRDao() {
		return iWRDao;
	}

}

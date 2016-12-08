package fr.pizzeria.dao.pizzadao;

import fr.pizzeria.dao.wrdao.MySQLWR;
import fr.pizzeria.exception.RemovePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMySQL extends IPizzaDao {

	public PizzaDaoMySQL() {
		super(new MySQLWR());
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws SavePizzaException {
		super.saveNewPizza(pizza);
		((MySQLWR) getIWRDao()).saveNewPizza(pizza.toString());
	}

	@Override
	public void updatePizza(int codePizza, Pizza pizza) throws UpdatePizzaException {
		Pizza pastPizza = findAllPizzas().get(codePizza);
		super.updatePizza(codePizza, pizza);
		((MySQLWR) getIWRDao()).updatePizza(pastPizza.toString(), pizza.toString());
	}

	@Override
	public void removePizza(int codePizza) throws RemovePizzaException {
		Pizza pastPizza = findAllPizzas().get(codePizza);
		super.removePizza(codePizza);
		((MySQLWR) getIWRDao()).removePizza(pastPizza.toString());
	}
	
	

}
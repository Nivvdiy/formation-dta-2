package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.LoadMemoriePizzaException;
import fr.pizzeria.exception.SaveMemoriePizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

public abstract class IWRDao {

	protected abstract void write() throws SaveMemoriePizzaException;

	protected abstract void read() throws LoadMemoriePizzaException;

	private List<String> lines;

	public List<String> getAllLines() {
		return lines;
	}

	public void setAllLines(List<String> list) {
		this.lines = list;
	}

	protected void setLines(List<String> lines) {
		this.lines = lines;
	}

	public List<String> getLines() {
		return lines;
	}

	public void addLine(String line) {
		lines.add(line);
	}

	public List<Pizza> toPizzaList() {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		for (String line : getLines()) {
			String[] temp = line.split(";");
			pizzas.add(new Pizza(temp[0], temp[1], Double.parseDouble(temp[2]), Category.parseCategory(temp[3]), true));
		}
		return pizzas;
	}

	public static List<String> toStringList(List<Pizza> list) {
		List<String> temps = new ArrayList<String>();
		for (Pizza pizza : list) {
			temps.add(pizza.getCode() + ";" + pizza.getName() + ";" + pizza.getPrice() + ";"
					+ pizza.getCategory().toString());
		}
		return temps;
	}

}

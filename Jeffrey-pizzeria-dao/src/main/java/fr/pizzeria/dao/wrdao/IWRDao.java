package fr.pizzeria.dao.wrdao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;

public abstract class IWRDao {

	private List<String> lines;		

	public abstract void write() throws PizzaException;

	public abstract void read() throws PizzaException;

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
		List<Pizza> pizzas = new ArrayList<>();
		for (String line : getLines()) {
			String[] temp = line.split(";");
			pizzas.add(new Pizza(temp[0], temp[1], Double.parseDouble(temp[2]), Category.parseCategory(temp[3]), true));
		}
		return pizzas;
	}

	public static List<String> toStringList(List<Pizza> list) {
		List<String> temps = new ArrayList<>();
		for (Pizza pizza : list) {
			temps.add(pizza.getCode() + ";" + pizza.getName() + ";" + pizza.getPrice() + ";"
					+ pizza.getCategory().toString());
		}
		return temps;
	}

}
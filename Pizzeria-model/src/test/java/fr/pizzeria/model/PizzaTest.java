package fr.pizzeria.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.pizzeria.model.Pizza.Category;

public class PizzaTest {

	@Test
	public void testGetNbPizza() {
		List<Pizza> pizzas = new ArrayList<>();
		List<Category> cat = new ArrayList<>();
		Category.getCatList().forEach((cate, categ) -> cat.add(categ));
		for (int i = 0; i < 120; i++) {
			pizzas.add(new Pizza("MAR", "Margharita", (i + 1) * 4.4, cat.get((int) ((Math.random() * 4))), true));
			System.out.println(pizzas.get(i).getCategory());
		}
		assertEquals(pizzas.size(), Pizza.getNbPizza());
	}

}

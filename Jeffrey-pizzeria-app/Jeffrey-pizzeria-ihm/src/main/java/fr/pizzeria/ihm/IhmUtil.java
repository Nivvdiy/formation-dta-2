package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.DaoFactory;
import fr.pizzeria.dao.pizzadao.PizzaDao;
import fr.pizzeria.model.Pizza;

public class IhmUtil {

	private Scanner scanner;
	private DaoFactory daoFactory;

	public IhmUtil(Scanner scanner, DaoFactory daoFactory) {

		this.scanner = scanner;
		this.daoFactory = daoFactory;
	}
	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public PizzaDao getPizzaDao() {
		return daoFactory.getPizzaDao();
	}

	public void setPizzaDao(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public void affichePizza(Pizza p, boolean index){
		if (index) {
			System.out.print(getPizzaDao().findAllPizzas().indexOf(p) + 1 + ".\t -> \t|");
		}
		System.out.print(p.getCode() + "|\t|" + p.getName());
		if (p.getName().length() < 31) {
			System.out.print("\t");
		}
		if (p.getName().length() < 23) {
			System.out.print("\t");
		}
		if (p.getName().length() < 15) {
			System.out.print("\t");
		}
		if (p.getName().length() < 7) {
			System.out.print("\t");
		}
		System.out.printf("|(" + "%.2f" + "€)|", p.getPrice());
		System.out.println(p.getCategory().getContent());
	}

}

package fr.pizzeria.ihm;

import java.util.Scanner;
import java.util.logging.Logger;

import fr.pizzeria.dao.pizzadao.IPizzaDao;
import fr.pizzeria.dao.wrdao.FileWR;
import fr.pizzeria.dao.wrdao.MySQLWR;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.Pizza;

public class IhmUtil {

	private Scanner scanner;
	private IPizzaDao iPizzaDao;

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public IPizzaDao getIPizzaDao() {
		return iPizzaDao;
	}

	public void setIPizzaDao(IPizzaDao iPizzaDao) {
		this.iPizzaDao = iPizzaDao;
	}

	public IhmUtil(Scanner scanner, IPizzaDao iPizzaDao) {
		this.scanner = scanner;
		this.iPizzaDao = iPizzaDao;
	}

	public void affichePizza(Pizza p, boolean index) {
		if (index) {
			System.out.print(iPizzaDao.findAllPizzas().indexOf(p) + 1 + ".\t -> \t|");
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

	public void initialize() {
		try {
			iPizzaDao.loadPizzas();
		} catch (PizzaException e) {
			Logger.getLogger(MySQLWR.class.getName()).severe(e.getMessage());
		}
	}

	public void savePizzaFile() {
		iPizzaDao.getIWRDao().setAllLines(FileWR.toStringList(iPizzaDao.findAllPizzas()));
		try {
			iPizzaDao.savePizzas();
		} catch (PizzaException e) {
			Logger.getLogger(MySQLWR.class.getName()).severe(e.getMessage());
		}
	}

}

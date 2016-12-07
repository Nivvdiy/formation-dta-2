package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.file.FileWR;
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
		iPizzaDao.loadPizzas();
	}

	public void savePizzaFile() {
		iPizzaDao.getIWRDao().setAllLines(FileWR.toStringList(iPizzaDao.findAllPizzas()));
		iPizzaDao.savePizzas();
	}

}

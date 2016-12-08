package fr.pizzeria.console;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import java.util.Scanner;

import fr.pizzeria.dao.pizzadao.IPizzaDao;
import fr.pizzeria.dao.pizzadao.PizzaDaoFile;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.ihm.MainMenu;

public class PizzeriaAdminConsoleApp {
	
	private PizzeriaAdminConsoleApp(){}

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException{

		ResourceBundle bundle = ResourceBundle.getBundle("app");
		String conf = bundle.getString("dao.impl");
		String memFile;

		IPizzaDao pizzaDao;
		if(Class.forName(conf) == PizzaDaoFile.class){
			memFile = bundle.getString("file");
			pizzaDao = (IPizzaDao) Class.forName(conf).getDeclaredConstructor(String.class).newInstance(memFile);
		} else {
			pizzaDao = (IPizzaDao) Class.forName(conf).newInstance();
		}
		
		System.out.println(conf+" => "+Class.forName(conf));

		IhmUtil ihmUtil = new IhmUtil(new Scanner(System.in), pizzaDao);

		MainMenu mainMenu = new MainMenu(ihmUtil);

		mainMenu.run();
	}

}

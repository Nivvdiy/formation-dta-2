package fr.pizzeria.console;

import java.util.logging.Level;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.pizzeria.ihm.MainMenu;

public class PizzeriaAdminConsoleApp {
	
	private PizzeriaAdminConsoleApp(){}

	public static void main(String[] args) throws Exception{
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		
		//try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(paths)) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PizzeriaAppSpringConfig.class)) {
			MainMenu mainMenu = context.getBean(MainMenu.class);

			mainMenu.run();

		}
	}

}

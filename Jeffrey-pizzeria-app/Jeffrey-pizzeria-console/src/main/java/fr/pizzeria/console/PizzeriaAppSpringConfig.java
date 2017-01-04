package fr.pizzeria.console;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.pizzeria.dao.pizzadao.PizzaDao;
import fr.pizzeria.dao.pizzadao.PizzaDaoRest;

@Configuration
@ComponentScan({"fr.pizzeria.ihm","fr.pizzeria.dao"})
public class PizzeriaAppSpringConfig {

	@Bean
	public PizzaDao getPizzaDao(){
		return new PizzaDaoRest();
	}
	
	@Bean
	public Scanner getScanner(){
		return new Scanner(System.in);
	}
}

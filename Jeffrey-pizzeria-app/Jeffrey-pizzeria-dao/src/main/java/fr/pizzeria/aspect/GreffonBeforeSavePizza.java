package fr.pizzeria.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import fr.pizzeria.model.Pizza;

@Component
@Aspect
public class GreffonBeforeSavePizza {

	@Before("fr.pizzeria.aspect.SavePizzaAspect.setRightPizzaCode()")
	public void beforeSavePizza(JoinPoint join){
		Pizza pizza = (Pizza) join.getArgs()[0];
		if(pizza.getCode()==null||pizza.getCode().length()!=3){
			pizza.setCode(pizza.getName().replaceAll(" ", "").substring(0, 3));
		}
		pizza.setCode(pizza.getCode().toUpperCase());
	}
	
}

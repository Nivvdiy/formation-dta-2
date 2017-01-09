package fr.pizzeria.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PizzaAspect {

	@Pointcut("execution(* saveNewPizza(..))")
	public void allSaveMethod(){}
	@Pointcut("execution(* updatePizza(..))")
	public void allUpdateMethod(){}
	@Pointcut("allUpdateMethod() || allSaveMethod()")
	public void setRightPizzaCode(){}

	@Pointcut("execution(* fr.pizzeria.dao.pizzadao.*.*(..))")
	public void allPizzaDao(){}
}

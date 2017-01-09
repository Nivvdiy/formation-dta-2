package fr.pizzeria.aspect;

import java.sql.Timestamp;
import java.util.Calendar;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.dao.performance.PerformanceDao;
import fr.pizzeria.model.Performance;
import fr.pizzeria.model.Pizza;

@Component
@Aspect
public class GreffonBeforePizza {
	
	@Autowired
	private PerformanceDao performanceDao;

	@Before("fr.pizzeria.aspect.PizzaAspect.setRightPizzaCode()")
	public void beforeSavePizza(JoinPoint join){
		Pizza pizza = (Pizza) join.getArgs()[0];
		if(pizza.getCode()==null||pizza.getCode().length()!=3){
			pizza.setCode(pizza.getName().replaceAll(" ", "").substring(0, 3));
		}
		pizza.setCode(pizza.getCode().toUpperCase());
	}
	
	@Around("fr.pizzeria.aspect.PizzaAspect.allPizzaDao()")
	public void profilerCreate(ProceedingJoinPoint pjp){
		Performance perf = new Performance();
		long startTime = Calendar.getInstance().getTimeInMillis();
		perf.setExecutionDate(new Timestamp(startTime));
		String target = pjp.getTarget().toString();
		perf.setService(target.substring(0, target.indexOf('@'))+" "+pjp.getSignature().getName());
		try {
			pjp.proceed();
			Long timer = Calendar.getInstance().getTimeInMillis() - startTime;
			perf.setExecutionTime(timer);
			performanceDao.savePerformance(perf);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
}

package fr.pizzeria.dao.performance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.model.Performance;
import fr.pizzeria.repos.PerformanceRepository;

@Component
public class PerformanceDao {
	
	@Autowired
	private PerformanceRepository perfRepo;
	
	public void savePerformance(Performance perf){
		perfRepo.save(perf);
	}
	
}

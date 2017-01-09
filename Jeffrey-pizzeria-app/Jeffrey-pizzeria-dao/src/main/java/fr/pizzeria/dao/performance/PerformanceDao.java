package fr.pizzeria.dao.performance;

import org.springframework.beans.factory.annotation.Autowired;

import fr.pizzeria.model.Performance;
import fr.pizzeria.repos.PerformanceRepository;

public class PerformanceDao {
	
	@Autowired
	private PerformanceRepository perfRepo;
	
	public void savePerformance(Performance perf){
		perfRepo.save(perf);
	}
	
}

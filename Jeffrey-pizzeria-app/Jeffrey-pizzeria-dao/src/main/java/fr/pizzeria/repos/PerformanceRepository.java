package fr.pizzeria.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Integer>{

}

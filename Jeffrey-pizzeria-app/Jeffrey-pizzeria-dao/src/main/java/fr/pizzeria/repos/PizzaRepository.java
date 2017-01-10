package fr.pizzeria.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import fr.pizzeria.model.Pizza;

@Component
public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

}

package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;

@Stateless
public class PizzaServiceEJB {

	@PersistenceContext(unitName="pizzeria") private EntityManager em;

	public void saveNewPizza(Pizza pizza) {
		em.persist(pizza);
	}

	public List<Pizza> findAllPizzas() {
		TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p ORDER BY name ASC", Pizza.class);
		Pizza.setNbPizza(query.getResultList().size());
		return query.getResultList();
	}

	public void deletePizza(String code) {
			TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p WHERE p.code = :code", Pizza.class);
			query.setParameter("code", code);
			Pizza p = query.getSingleResult();
			if(p != null){
				em.remove(p);
			}
		
	}

	public void updatePizza(String code, Pizza p) {
		findAllPizzas().forEach(pizza -> {
			if (pizza.getCode().equals(code)) {
				em.merge(p);
			}
		});
		
	}

}

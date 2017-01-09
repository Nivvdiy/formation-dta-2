package fr.pizzeria.dao.pizzadao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Pizza;

public class PizzaDaoJPA implements PizzaDao {

	@FunctionalInterface
	interface IRunEM {
		void exec(EntityTransaction transac, EntityManager em);
	}

	@FunctionalInterface
	interface IRunEMPrep {
		void exec(EntityManager em);
	}

	private List<Pizza> listPizzas = new ArrayList<>();
	private EntityManagerFactory emf;

	public PizzaDaoJPA(){
		emf = Persistence.createEntityManagerFactory("JPAPizza");
	}

	private void reloadPizza() {
		Pizza.setNbPizza(0);
		execute((em) -> {
			TypedQuery<Pizza> query = em.createQuery("SELECT p FROM Pizza p ORDER BY name ASC", Pizza.class);
			Pizza.setNbPizza(query.getResultList().size());
			listPizzas = query.getResultList();
		});
	}

	public void executeTrans(IRunEM run){
		EntityManager em = emf.createEntityManager();
		EntityTransaction transac = em.getTransaction();
		transac.begin();
		run.exec(transac, em);
		transac.commit();
	}


	public void execute(IRunEMPrep run){
		EntityManager em = emf.createEntityManager();
		run.exec(em);
		em.close();

	}

	@Override
	public List<Pizza> findAllPizzas() {
		reloadPizza();
		return listPizzas;
	}

	@Override
	public void saveNewPizza(Pizza pizza){
		executeTrans((transac, em) -> em.persist(pizza));
		listPizzas.add(pizza);
	}

	@Override
	public void updatePizza(Pizza lastPizzaState, Pizza newPizzaState) {
		executeTrans((transac, em) -> em.merge(newPizzaState));
		listPizzas.set(listPizzas.indexOf(lastPizzaState), newPizzaState);
	}

	@Override
	public void deletePizza(Pizza deletedPizza) {
		executeTrans((trans, em) -> em.remove(deletedPizza));
		listPizzas.remove(deletedPizza);
		Pizza.setNbPizza(Pizza.getNbPizza()-1);
	}
}


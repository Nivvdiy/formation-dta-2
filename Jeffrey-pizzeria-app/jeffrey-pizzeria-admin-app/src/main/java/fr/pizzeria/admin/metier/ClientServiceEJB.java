package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Client;

@Stateless
public class ClientServiceEJB {

	@FunctionalInterface
	interface IRunEM {
		void exec(EntityTransaction transac, EntityManager emf);
	}

	@PersistenceContext(unitName="pizzeria") private EntityManager em;

	public void executeTrans(IRunEM run){
		EntityManager emf = em.getEntityManagerFactory().createEntityManager();
		EntityTransaction transac = emf.getTransaction();
		transac.begin();
		run.exec(transac, emf);
		transac.commit();
	}

	public void saveNewClient(Client client) {
		em.persist(client);
	}

	public List<Client> findAllClients() {
		TypedQuery<Client> query = em.createQuery("SELECT p FROM Client p ORDER BY nom ASC", Client.class);
		Client.setNbClient(query.getResultList().size());
		return query.getResultList();
	}
	
	public Client findClient(int id) {
		TypedQuery<Client> query = em.createQuery("SELECT p FROM Client p WHERE p.id = :id ORDER BY nom ASC", Client.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	public void deleteClient(int id) {
			TypedQuery<Client> query = em.createQuery("SELECT p FROM Client p WHERE p.id = :id", Client.class);
			query.setParameter("id", id);
			Client p = query.getSingleResult();
			if(p != null){
				em.remove(p);
			}
		
	}

	public void updateClient(int id, Client p) {
		findAllClients().forEach(client -> {
			if (client.getId() == id) {
				em.merge(p);
			}
		});
		
		
	}

}

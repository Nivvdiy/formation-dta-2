package fr.pizzeria.dao.pizzadao;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.pizzeria.model.Pizza;

public class PizzaDaoRest implements PizzaDao {

	private Client client;
	private WebTarget target;
	private Builder builder;

	public PizzaDaoRest(){
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080").path("jeffrey-pizzeria-admin-app").path("api").path("rest");
		findAllPizzas();
	}

	@Override
	public List<Pizza> findAllPizzas() {
		builder = target.path("pizzas").request(MediaType.APPLICATION_JSON_TYPE);
		Response response = builder.get();
		List<Pizza> readEntity = response.readEntity(new GenericType<List<Pizza>>(){});
		Pizza.setNbPizza(readEntity.size());
		return readEntity;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		builder = target.path("pizzas").request();
		builder.post(Entity.json(pizza));
	}

	@Override
	public void updatePizza(Pizza lastPizzaState, Pizza newPizzaState) {
		builder = target.path("pizzas").path(Integer.toString(lastPizzaState.getId())).request();
		builder.put(Entity.json(newPizzaState));
	}

	@Override
	public void deletePizza(Pizza deletedPizza) {
		builder = target.path("pizzas").path(Integer.toString(deletedPizza.getId())).request();
		builder.delete();
		Pizza.setNbPizza(Pizza.getNbPizza()-1);
	}

}

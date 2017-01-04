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
	private List<Pizza> pizzas;

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
		pizzas = readEntity;
		return readEntity;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		builder = target.path("pizzas").request();
		builder.post(Entity.json(pizza));
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		builder = target.path("pizzas").path(Integer.toString(pizza.getId())).request();
		builder.put(Entity.json(pizza));
	}

	@Override
	public void deletePizza(String codePizza) {
		pizzas.forEach(p->{
			if(p.getCode().equals(codePizza)){
				builder = target.path("pizzas").path(Integer.toString(p.getId())).request();
			}
		});
		builder.delete();
	}

	@Override
	public void updatePizza(int codePizza, Pizza pizza) {
		updatePizza(pizzas.get(codePizza-1).getCode(), pizza);
	}

	@Override
	public void deletePizza(int codePizza) {
		deletePizza(pizzas.get(codePizza-1).getCode());
	}

}

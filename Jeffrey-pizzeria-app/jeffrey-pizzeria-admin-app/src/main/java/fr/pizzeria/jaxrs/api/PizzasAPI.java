package fr.pizzeria.jaxrs.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.pizzeria.admin.metier.PizzaServiceEJB;
import fr.pizzeria.model.Pizza;

@Path("/pizzas")
public class PizzasAPI {
	
	@EJB
	private PizzaServiceEJB pizzaServiceEJB;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> getPizzas(){
		return pizzaServiceEJB.findAllPizzas();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pizza getPizza(@PathParam("id") int id){
		return pizzaServiceEJB.findPizza(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPizza(Pizza p){
		pizzaServiceEJB.saveNewPizza(p);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatePizza(@PathParam("id") int id, Pizza p){
		pizzaServiceEJB.updatePizza(id, p);
	}
	
	@DELETE
	@Path("/{id}")
	public void deletePizza(@PathParam("id") int id){
		pizzaServiceEJB.deletePizza(id);
	}
	
}

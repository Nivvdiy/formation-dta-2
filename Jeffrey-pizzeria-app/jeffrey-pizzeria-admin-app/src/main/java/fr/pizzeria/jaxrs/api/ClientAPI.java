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

import fr.pizzeria.admin.metier.ClientServiceEJB;
import fr.pizzeria.model.Client;

@Path("/clients")
public class ClientAPI {
	
	@EJB
	private ClientServiceEJB clientServiceEJB;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Client> getClients(){
		return clientServiceEJB.findAllClients();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Client getClient(@PathParam("id") int id){
		return clientServiceEJB.findClient(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addClient(Client p){
		clientServiceEJB.saveNewClient(p);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateClient(@PathParam("id") int id, Client p){
		clientServiceEJB.updateClient(id, p);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteClient(@PathParam("id") int id){
		clientServiceEJB.deleteClient(id);
	}
	
}

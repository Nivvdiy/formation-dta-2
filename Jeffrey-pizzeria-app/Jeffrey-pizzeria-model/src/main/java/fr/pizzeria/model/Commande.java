package fr.pizzeria.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@NamedQuery(name="Commande.findAll", query="SELECT c FROM Commande c")
public class Commande {

	public enum Statut{
		PREPARATION,LIVRAISON,LIVRE,ATTENTE_PREPARATION,ATTENTE_LIVREUR
	}

	@Id
	private byte id;
	@Column(columnDefinition = "enum('PREPARATION','LIVRAISON','LIVRE','ATTENTE_PREPARATION','ATTENTE_LIVREUR')")
	@Enumerated(EnumType.ORDINAL)
	private Statut statut;
	private Date commandeDate;
	private String numeroDeCommande;

	@ManyToMany

	@JoinTable(name="commande_pizza",
	joinColumns=
	@JoinColumn(name="ID_Commande", referencedColumnName="ID"),
	inverseJoinColumns=
	@JoinColumn(name="ID_Pizza", referencedColumnName="ID")
			)
	private Set<Pizza> pizzas;

	@ManyToOne
	@JoinColumn(name="ID_Client")
	private Client client;

	@ManyToOne
	@JoinColumn(name="ID_Livreur")
	private Livreur livreur;

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Date getCommandeDate() {
		return commandeDate;
	}

	public void setCommandeDate(Date commandeDate) {
		this.commandeDate = commandeDate;
	}

	public String getNumeroDeCommande() {
		return numeroDeCommande;
	}

	public void setNumeroDeCommande(String numeroDeCommande) {
		this.numeroDeCommande = numeroDeCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	public Set<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Set<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

}
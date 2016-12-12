package fr.pizzeria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the livreur database table.
 * 
 */
@Entity
@NamedQuery(name="Livreur.findAll", query="SELECT l FROM Livreur l")
public class Livreur {

	@Id
	private byte id;
	private String nom;
	private String prenom;

	//bi-directional many-to-one association to Commande
	@OneToMany(mappedBy="livreur")
	private List<Commande> commandes;

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

}
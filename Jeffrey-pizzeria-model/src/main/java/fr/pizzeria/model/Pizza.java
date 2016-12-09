package fr.pizzeria.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Pizza {

	public enum Category {
		VIANDE("Viande"), POISSON("Poisson"), VEGETARIENNE("Végétarienne"), SANS_VIANDE("Sans viande");
		private String content;
		private static Map<String, Category> catList = new HashMap<>();
		static {
			catList.put("VIANDE", VIANDE);
			catList.put("POISSON", POISSON);
			catList.put("VEGETARIENNE", VEGETARIENNE);
			catList.put("SANS_VIANDE", SANS_VIANDE);
		}

		private Category(String content) {
			this.content = content;
		}

		public String getContent() {
			return content;
		}

		public static Category parseCategory(String cat) {
			return catList.get(cat);
		}

		public static Map<String, Category> getCatList() {
			return catList;
		}
	}

	private static int nbPizza;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String code;
	private String name;
	private double price;
	@Enumerated
	private Category category;

	public Pizza(String code, String name, double price, Category category, boolean indent) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.category = category;
		if(indent){
			nbPizza++;
		}
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public static int getNbPizza() {
		return nbPizza;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return code + ";" + name + ";" + price + ";" + category;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Pizza rhs = (Pizza) obj;
		return new EqualsBuilder().append(this.getCode(), rhs.getCode()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(name).append(code).toHashCode();
	}

	public static void setNbPizza(int nbPizza) {
		Pizza.nbPizza = nbPizza;
	}

}

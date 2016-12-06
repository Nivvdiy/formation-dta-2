package fr.pizzeria.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;

public class Pizza {

	public enum Category {
		VIANDE("Viande"), POISSON("Poisson"), VEGETARIENNE("Végétarienne"), SANS_VIANDE("Sans viande");
		private String content;
		private static Map<String, Category> catList = new HashMap<String, Category>();
		static {
			catList.put("VIANDE", VIANDE);
			catList.put("POISSON", POISSON);
			catList.put("VEGETARIENNE", VEGETARIENNE);
			catList.put("SANS_VIANDE", SANS_VIANDE);
		}

		public String getContent() {
			return content;
		}

		private Category(String content) {
			this.content = content;
		}

		public static Category parseCategory(String cat) {
			return catList.get(cat);
		}

		public static Map<String, Category> getCatList() {
			return catList;
		}
	}

	private static int nbPizza;
	private String code, name;
	private double price;
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public static int getNbPizza() {
		return nbPizza;
	}

	public static void addPizza() {
		nbPizza++;
	}

	public static void removePizza() {
		nbPizza--;
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

	public Pizza(String code, String name, double price, Category category, boolean increment) {
		super();
		if (increment) {
			addPizza();
		}
		this.code = code;
		this.name = name;
		this.price = price;
		this.category = category;
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

}

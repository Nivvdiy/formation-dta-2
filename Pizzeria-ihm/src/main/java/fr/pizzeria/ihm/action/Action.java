package fr.pizzeria.ihm.action;

import fr.pizzeria.ihm.IhmUtil;

public abstract class Action {

	private String title;
	protected IhmUtil ihmUtil;
	private String description;

	public Action(String description, String title, IhmUtil ihmUtil) {
		this.setTitle(title);
		this.setDescription(description);
		this.ihmUtil = ihmUtil;
	}

	public IhmUtil getIhmUtil() {
		return ihmUtil;
	}

	public void setIhmUtil(IhmUtil ihmUtil) {
		this.ihmUtil = ihmUtil;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void afficheTitre() {
		System.out.println("***** " + getTitle() + " *****");
	}

	public void describeAction() {
		System.out.println(getDescription());
	}

	public abstract void doAction();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
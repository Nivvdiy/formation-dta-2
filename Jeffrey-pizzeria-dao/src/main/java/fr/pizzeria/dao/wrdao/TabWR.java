package fr.pizzeria.dao.wrdao;

import java.util.ArrayList;
import java.util.List;

public class TabWR extends IWRDao {

	private List<String> pizzaStrList;

	public TabWR() {
		this.pizzaStrList = new ArrayList<>();
		pizzaStrList.add("MAR;Margarita;15.7;VIANDE");
		pizzaStrList.add("POV;Poivrons;14.3;VEGETARIENNE");
		pizzaStrList.add("ROM;Roma;14.8;VIANDE");
		pizzaStrList.add("CHR;Chorizo;15.95;VIANDE");
		pizzaStrList.add("NAP;Napolitaine;15.95;VIANDE");
		this.setLines(new ArrayList<String>());
	}

	@Override
	public void write() {
		getLines().forEach((str) -> pizzaStrList.add(str));
	}

	@Override
	public void read() {
		pizzaStrList.forEach((str) -> super.addLine(str));
	}

}

package fr.pizzeria.ihm.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.pizzeria.dao.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

@Repository
public class ListPizza extends Action {

	@Autowired
	public ListPizza(IhmUtil ihmUtil) {
		super("Lister les pizzas", "Liste des pizzas", ihmUtil);
	}

	@Override
	public void doAction(){
		this.afficheTitre();
		ihmUtil.getPizzaDao().findAllPizzas();
		if (Pizza.getNbPizza() == 0) {
			System.out.println("\nAucune pizza dans la liste\n");
		} else {
			ihmUtil.getPizzaDao().findAllPizzas().forEach((p) -> {
				try {
					getIhmUtil().affichePizza(p, false);
				} catch (PizzaException e) {
					e.printStackTrace();
				}
			});
		}
	}

}

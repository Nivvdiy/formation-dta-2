package fr.pizzeria.admin.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.Pizza.Category;
import fr.pizzeria.repos.PizzaRepository;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaRepository pizzaDao;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Pizza> listPizzas(){
		return pizzaDao.findAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Pizza getPizza( @PathVariable Integer id){
		return pizzaDao.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addPizza(@ModelAttribute("pizza") Pizza pizza){
		List<Pizza> listPizzas = listPizzas();
		if(pizza.getName()==null&&pizza.getCode()==null){
			return "empty";
		} else if(pizza.getName()==null){
			return "emptyName";
		} else if(isExist(listPizzas, pizza)){
			return "exist";
		} else {
			if(pizza.getPrice()==null){
				pizza.setPrice(0.0);
			}
			if(pizza.getCode()==null){
				pizza.setCode(pizza.getName().substring(0, 3).toUpperCase());
			}
			if(pizza.getCategory()==null){
				pizza.setCategory(Category.VIANDE);
			}
			pizzaDao.save(pizza);
			return "success";
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public String changePizza(@ModelAttribute("pizza") Pizza pizza){
		List<Pizza> listPizzas = listPizzas();
		if(pizza.getId()==null||pizza.getId()==0){
			return "fakeID";
		} else if (!isPresent(listPizzas, pizza)){
			return "inexistantID";
		} else {
			Pizza pizza2 = getPizza(pizza.getId());
			if(pizza.getPrice()!=null){
				pizza2.setPrice(pizza.getPrice());
			}
			if(pizza.getCode()!=null){
				pizza2.setCode(pizza.getCode());
			}
			if(pizza.getCategory()!=null){
				pizza2.setCategory(pizza.getCategory());
			}
			if(pizza.getImage()!=null){
				pizza2.setImage(pizza.getImage());
			}
			if(pizza.getName()!=null){
				pizza2.setName(pizza.getName());
			}
			pizzaDao.save(pizza2);
			return "success";
		}
	}

	private boolean isPresent(List<Pizza> listPizzas, Pizza pizza) {
		for(Pizza ing:listPizzas){
			if(ing.getId()==pizza.getId()){
				return true;
			}
		}
		return false;
	}

	private boolean isExist(List<Pizza> listPizzas, Pizza pizza) {
		for(Pizza ing:listPizzas){
			if((pizza.getId()!=null && pizza.getId()==ing.getId()) ||
					(pizza.getName().equals(ing.getName()) || pizza.getName().equals(ing.getName().substring(0,ing.getName().length()-1)))){
				return true;
			}
		}
		return false;
	}

}

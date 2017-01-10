package fr.pizzeria.admin.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.pizzeria.dao.ingredients.IngredientDao;
import fr.pizzeria.model.Ingredient;

@Controller
@RequestMapping("/rest/ingredients")
public class IngredientsController {

	@Autowired
	private IngredientDao ingredientDao;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Ingredient> listIngredients(){
		return ingredientDao.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addIngredient(@ModelAttribute("ingredient") Ingredient ingredient){
		List<Ingredient> listIngredients = listIngredients();
		if((ingredient.getName()==null&&ingredient.getPrice()==null&&ingredient.getQuantity()==null)){
			return "empty";
		} else if(ingredient.getName()==null){
			return "emptyName";
		} else if(isPresent(listIngredients, ingredient)){
			return "exist";
		} else {
			if(ingredient.getPrice()==null){
				ingredient.setPrice(0.0);
			}
			if(ingredient.getQuantity()==null){
				ingredient.setQuantity(0.0);
			}
			ingredientDao.saveNew(ingredient);
			return "success";
		}
	}

	private boolean isPresent(List<Ingredient> listIngredients, Ingredient ingredient) {
		for(Ingredient ing:listIngredients){
			if((ingredient.getId()!=null && ingredient.getId()==ing.getId()) ||
					(ingredient.getName().equals(ing.getName()) || ingredient.getName().equals(ing.getName().substring(0,ing.getName().length()-1)))){
				return true;
			}
		}
		return false;
	}

}

package fr.pizzeria.admin.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.pizzeria.dao.ingredients.IngredientDao;
import fr.pizzeria.model.Ingredient;

@Controller
@RequestMapping("/ingredients")
public class IngredientsController {

	@Autowired
	private IngredientDao ingredientDao;

	/*@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Ingredient> listIngredients(){
		return ingredientDao.findAll();
	}*/

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listPeople(Model model) {
		Ingredient ingredient = new Ingredient();
		ModelAndView mav = new ModelAndView();
		model.addAttribute("ingredient",ingredient);
		mav.setViewName("test");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String addIngredient(@ModelAttribute("ingredient") Ingredient ingredient){
		if(ingredient.getName()!=null&&ingredient.getPrice()!=null&&ingredient.getQuantity()!=null){
			ingredientDao.saveNew(ingredient);
			return "success";
		} else {
			return "empty";
		}
	}

}

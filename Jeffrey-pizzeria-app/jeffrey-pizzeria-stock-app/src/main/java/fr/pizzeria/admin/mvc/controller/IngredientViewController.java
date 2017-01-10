package fr.pizzeria.admin.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.pizzeria.model.Ingredient;

@Controller
@RequestMapping("/ingredients")
public class IngredientViewController {

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView editPeople(Model model, @PathVariable Integer id) {
		Ingredient ingredient = new Ingredient();
		ModelAndView mav = new ModelAndView();
		model.addAttribute("ingredient",ingredient);
		mav.setViewName("editIngredientForm");
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listPeople(Model model) {
		Ingredient ingredient = new Ingredient();
		ModelAndView mav = new ModelAndView();
		model.addAttribute("ingredient",ingredient);
		mav.setViewName("editIngredientForm");
		return mav;
	}
}

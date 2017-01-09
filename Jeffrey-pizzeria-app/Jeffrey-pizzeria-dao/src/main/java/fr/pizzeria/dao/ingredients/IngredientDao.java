package fr.pizzeria.dao.ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.model.Ingredient;
import fr.pizzeria.repos.IngredientRepository;

@Component
public class IngredientDao {
	
	@Autowired
	private IngredientRepository ingredientRepo;

	public List<Ingredient> findAll(){
		return ingredientRepo.findAll();
	}

	public void saveNew(Ingredient ingredient){
		ingredientRepo.save(ingredient);
	}
	
	public void update(Ingredient lastIngredientState, Ingredient newIngredientState){
		ingredientRepo.save(newIngredientState);
	}
	
	public void delete(Ingredient deletedIngredient){
		ingredientRepo.delete(deletedIngredient);
	}
	
	

}

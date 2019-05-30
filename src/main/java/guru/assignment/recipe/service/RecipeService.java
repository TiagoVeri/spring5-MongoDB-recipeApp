package guru.assignment.recipe.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.assignment.recipe.commands.RecipeCommand;
import guru.assignment.recipe.domain.Recipe;


public interface RecipeService {

	Set<Recipe> getRecipe();

	Recipe findById(Long l);
	
	RecipeCommand findCommandById(Long l);
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);

	void deleteById(Long idToDelete);
}

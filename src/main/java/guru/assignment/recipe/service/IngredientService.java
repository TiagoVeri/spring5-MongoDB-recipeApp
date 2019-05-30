package guru.assignment.recipe.service;

import guru.assignment.recipe.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findByRecipeIdandIngredientId(String recipeId, String ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand command);

	void deleteById(String recipeId, String idToDelete);
}

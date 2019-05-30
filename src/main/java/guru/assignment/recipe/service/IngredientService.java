package guru.assignment.recipe.service;

import guru.assignment.recipe.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findByRecipeIdandIngredientId(Long recipeId, Long ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand command);

	void deleteById(Long recipeId, Long idToDelete);
}

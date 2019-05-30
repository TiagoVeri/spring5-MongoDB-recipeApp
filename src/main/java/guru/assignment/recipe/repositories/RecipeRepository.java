package guru.assignment.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.assignment.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}

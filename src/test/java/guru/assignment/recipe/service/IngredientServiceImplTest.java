package guru.assignment.recipe.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import guru.assignment.recipe.commands.IngredientCommand;
import guru.assignment.recipe.converters.IngredientCommandToIngredient;
import guru.assignment.recipe.converters.IngredientToIngredientCommand;
import guru.assignment.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.assignment.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.assignment.recipe.domain.Ingredient;
import guru.assignment.recipe.domain.Recipe;
import guru.assignment.recipe.repositories.RecipeRepository;
import guru.assignment.recipe.repositories.UnitOfMeasureRepository;

public class IngredientServiceImplTest {

	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	IngredientService ingredientService;
	
	
	//init converters
	public IngredientServiceImplTest() {
		this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
		this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand,ingredientCommandToIngredient,
											recipeRepository, unitOfMeasureRepository);
	}

	@Test
	public void testFindByRecipeIdandIngredientId() {
	}

	@Test
	public void findByRecipeIdAndReceipeIdHappyPath() throws Exception{
		//given
		Recipe recipe = new Recipe();
		recipe.setId("1");
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId("1");
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId("2");
		
		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId("3");
		
		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
		
		//then
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdandIngredientId("1", "3");
		
		//when
		assertEquals(Long.valueOf(3L),ingredientCommand.getId());
		assertEquals(Long.valueOf(1L),ingredientCommand.getRecipeId());
		verify(recipeRepository, times(1)).findById(anyString());
		
	}
	
	@Test
	public void testSaveRecipeCommand() throws Exception{
		//given
		IngredientCommand command = new IngredientCommand();
		command.setId("3");
		command.setRecipeId("2");
		
		Optional<Recipe> recipeOptional = Optional.of(new Recipe());
		
		Recipe savedRecipe = new Recipe();
		savedRecipe.addIngredient(new Ingredient());
		savedRecipe.getIngredients().iterator().next().setId("3");
		
		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
		when(recipeRepository.save(any())).thenReturn(savedRecipe);
		
		//when
		IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);
		
		//then
		assertEquals(Long.valueOf(3L), savedCommand.getId());
		verify(recipeRepository,times(1)).findById(anyString());
		verify(recipeRepository,times(1)).save(any(Recipe.class));
	}
	
	@Test
	public void testDeleteById() throws Exception{
		
		//given
		Recipe recipe = new Recipe();
		Ingredient ingredient = new Ingredient();
		ingredient.setId("3");
		recipe.addIngredient(ingredient);
//		ingredient.setRecipe(recipe);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyString())).thenReturn(recipeOptional);
		
		//when
		ingredientService.deleteById("1", "3");
		//then
		verify(recipeRepository, times(1)).findById(anyString());
		verify(recipeRepository, times(1)).save(any(Recipe.class));

	}
}

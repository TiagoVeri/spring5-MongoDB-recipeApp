package guru.assignment.recipe.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.assignment.recipe.converters.RecipeCommandToRecipe;
import guru.assignment.recipe.converters.RecipeToRecipeCommand;
import guru.assignment.recipe.domain.Recipe;
import guru.assignment.recipe.exceptions.NotFoundException;
import guru.assignment.recipe.repositories.RecipeRepository;

public class RecipeServiceImplTest {
	
	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;


	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		
		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}
	
	@Test
	public void testGetRecipeById() throws Exception{
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
		Recipe recipeReturned = recipeService.findById(1L);
		
		assertNotNull("Null recipe returned", recipeReturned);
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();
		

	}
	
	@Test(expected = NotFoundException.class)
	public void getRecipeByIdTestNotFound() throws Exception{
	
		Optional<Recipe> recipeOptional = Optional.empty();
		
		when (recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		
		Recipe recipeReturned = recipeService.findById(1L);
	}
	
	
	@Test
	public void testGetRecipe() throws Exception{
		
		Recipe recipe = new Recipe();
		HashSet recipesData = new HashSet();
		recipesData.add(recipe);
		
	
		when(recipeRepository.findAll()).thenReturn(recipesData);
		
		Set<Recipe> recipes = recipeService.getRecipe();
		
		assertEquals(recipes.size(), 1);
		
//		testa se o findAll é chamado apenas 1 vez, não 2 ou 0, mas 1.
		verify(recipeRepository, times(1)).findAll();
	}
	
	@Test
	public void testDeleteById() throws Exception{
		
		//given
		Long idToDelete = Long.valueOf(2L);
		
		//when
		recipeService.deleteById(idToDelete);
		
		//no 'when', since method has void return type
		
		//then
		verify(recipeRepository, times(1)).deleteById(anyLong());
	}

}

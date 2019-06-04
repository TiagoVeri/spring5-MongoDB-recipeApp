package guru.assignment.recipe.repositories;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.assignment.recipe.bootstap.RecipeBoostrap;
import guru.assignment.recipe.domain.UnitOfMeasure;

//Integration Test 
@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Before
	public void setUp() throws Exception{
		
		recipeRepository.deleteAll();
		unitOfMeasureRepository.deleteAll();
		categoryRepository.deleteAll();
		
		RecipeBoostrap recipeBootstrap = new RecipeBoostrap(categoryRepository, recipeRepository, unitOfMeasureRepository);
		
		recipeBootstrap.onApplicationEvent(null);
	}
	
	//Vai verificar no banco se consegue achar o falor descrito com Optional
	@Test
	public void testFindByDescription() throws Exception{
		
		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
		
		assertEquals("Teaspoon", uomOptional.get().getDescription());
	}

}

package guru.assignment.recipe.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category category;
	
	@Before
	public void setUp() {
		category = new Category();
	}
	@Test
	public void testGetId() throws Exception {
		String idValue = "4";
		
		category.setId(idValue);
		
		assertEquals(idValue, category.getId());
	}

	@Test
	public void testGetDescription() throws Exception {
	
	}

	@Test
	public void testGetRecipes() throws Exception{
		
	}

}

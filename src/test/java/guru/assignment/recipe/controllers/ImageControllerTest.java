package guru.assignment.recipe.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import guru.assignment.recipe.commands.RecipeCommand;
import guru.assignment.recipe.service.ImageService;
import guru.assignment.recipe.service.RecipeService;

public class ImageControllerTest {

	@Mock
	ImageService imageService;
	
	@Mock
	RecipeService recipeService;
	
	ImageController controller;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		
		controller = new ImageController(imageService,recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(new ControllerExceptionHandler())
				.build();
	}
	
	@Test
	public void getImageForm() throws Exception{
		//given
		RecipeCommand command = new RecipeCommand();
		command.setId("1");
		
		when(recipeService.findCommandById(anyString())).thenReturn(command);
		
		//when
		mockMvc.perform(get("/recipe/1/image"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipe"));
		
		verify(recipeService, times(1)).findCommandById(anyString());
	}
	
	@Test
	public void handleImagePost() throws Exception{
		MockMultipartFile multipartFile =
				new MockMultipartFile("imagefile", "testing.txt", "text/plain",
						"Spring Framework Guru".getBytes());
		
		mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
				.andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location", "/recipe/1/show"));
		
		verify(imageService, times(1)).saveImageFile(anyString(),any());
	}
	
	@Test
	public void renderImageFromDB() throws Exception{
		
		//given
		RecipeCommand command = new RecipeCommand();
		command.setId("1");
		
		String s = "fake image text";
		Byte[] bytesBoxed = new Byte[s.getBytes().length];
		
		int i = 0;
		
		for(byte primByte : s.getBytes()) {
			bytesBoxed[i++] = primByte;
		}
		
		command.setImage(bytesBoxed);
		
		when(recipeService.findCommandById(anyString())).thenReturn(command);
		
		//when
		MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
				.andExpect(status().isOk())
				.andReturn().getResponse();
		
		byte[] responseBytes = response.getContentAsByteArray();
		
		assertEquals(s.getBytes().length, responseBytes.length);
	}
	
	@Test
	public void testGetImageNumberFormatException() throws Exception{
		
		mockMvc.perform(get("/recipe/asf/recipeimage"))
		 		.andExpect(status().isBadRequest())
		 		.andExpect(view().name("400error")); 
	}
}

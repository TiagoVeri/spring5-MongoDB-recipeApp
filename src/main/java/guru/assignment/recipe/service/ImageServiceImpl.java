package guru.assignment.recipe.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import guru.assignment.recipe.domain.Recipe;
import guru.assignment.recipe.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	private final RecipeRepository recipeRepository;
	
	
	public ImageServiceImpl(RecipeRepository recipeService) {
		this.recipeRepository = recipeService;
	}


	@Override
	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file) {
		
		try {
			Recipe recipe = recipeRepository.findById(recipeId).get();
			
			Byte[] byteObjects = new Byte[file.getBytes().length];
			
			//for each convert java primative to wrapper (class) object
			int i = 0;
			
			for(byte b : file.getBytes()) {
				byteObjects[i++] = b;
			}
			
			recipe.setImage(byteObjects);
			
			recipeRepository.save(recipe);
			
		} catch (IOException e) {
			//todo handle better
			log.error("Error ocurred", e);
			
			e.printStackTrace();
		}

	}

}

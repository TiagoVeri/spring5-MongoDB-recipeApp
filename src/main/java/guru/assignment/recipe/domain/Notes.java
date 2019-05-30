package guru.assignment.recipe.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notes {

	@Id
	private String id;
	
	private Recipe recipe;
	
	private String recipeNotes;

	
}

package guru.assignment.recipe.domain;

import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude= {"recipes"})
public class Category {

	private String id;
	private String description;

	private Set<Recipe> recipes;

	
	
}

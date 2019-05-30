package guru.assignment.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.assignment.recipe.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findByDescription(String description);

}

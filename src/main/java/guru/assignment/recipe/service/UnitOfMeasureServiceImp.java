package guru.assignment.recipe.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.assignment.recipe.commands.UnitOfMeasureCommand;
import guru.assignment.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.assignment.recipe.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImp implements UnitOfMeasureService {

	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	
	public UnitOfMeasureServiceImp(UnitOfMeasureRepository unitOfMeasureRepository,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}


	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {

		return StreamSupport.stream(unitOfMeasureRepository.findAll()
							.spliterator(), false)
							.map(unitOfMeasureToUnitOfMeasureCommand :: convert)
							.collect(Collectors.toSet());
	}

}

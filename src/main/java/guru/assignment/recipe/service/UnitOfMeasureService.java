package guru.assignment.recipe.service;

import java.util.Set;

import guru.assignment.recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();
}

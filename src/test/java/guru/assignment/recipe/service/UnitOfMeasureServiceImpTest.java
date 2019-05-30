package guru.assignment.recipe.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.assignment.recipe.commands.UnitOfMeasureCommand;
import guru.assignment.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.assignment.recipe.domain.UnitOfMeasure;
import guru.assignment.recipe.repositories.UnitOfMeasureRepository;

public class UnitOfMeasureServiceImpTest {

	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
	UnitOfMeasureService service;
	
	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		service = new UnitOfMeasureServiceImp(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
	}

	@Test
	public void testListAllUoms() {
		//given
		Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(1L);
		unitOfMeasures.add(uom1);
		
		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom2.setId(2L);
		unitOfMeasures.add(uom2);
		
		when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);
		
		//when
		Set<UnitOfMeasureCommand> commands = service.listAllUoms();
		
		//then
		assertEquals(2, commands.size());
		verify(unitOfMeasureRepository, times(1)).findAll();
		
	}

}

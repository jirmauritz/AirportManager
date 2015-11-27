package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardSimpleDTO;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import cz.muni.fi.pa165.airport_manager.service.StewardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */

@RunWith(MockitoJUnitRunner.class)
public class StewardFacadeTest {

    private @Mock StewardService stewardService;
    private @Mock MappingService mappingService;

    @InjectMocks
    private StewardFacade stewardFacade =
            new StewardFacadeImpl();

    private Steward steward;
	private Steward updatedSteward;
	private StewardSimpleDTO stewardDTO;
	private StewardCreateDTO stewardCreateDTO;
	private StewardDTO stewardRichDTO;
	private StewardDTO updatedRichSteward;
	
	private Set<Steward> set;
	private FlightDTO flight = new FlightDTO();
	
	private final Long B_ID = 1l;
	private final String NAME = "Vaclav";
	private final String SURNAME = "Vaclav";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
		
		// create objects
		steward = new Steward();
		steward.setBusinessId(1l);
		steward.setFirstName("Vaclav");
		steward.setLastName("Havel");
		
		set = new HashSet<>();
		set.add(steward);

		stewardDTO = new StewardSimpleDTO();
		stewardDTO.setBusinessId(B_ID);
		stewardDTO.setFirstName(NAME);
		stewardDTO.setLastName(SURNAME);
		
        stewardCreateDTO = new StewardCreateDTO();
		stewardCreateDTO.setFirstName(NAME);
		stewardCreateDTO.setLastName(SURNAME);
		
		stewardRichDTO = new StewardDTO();
		stewardRichDTO.setBusinessId(B_ID);
		stewardRichDTO.setFirstName(NAME);
		stewardRichDTO.setLastName(SURNAME);
		Set<FlightDTO> flights = new HashSet<>(Arrays.asList(flight));
		stewardRichDTO.setFlights(flights);
		
		updatedRichSteward = new StewardDTO();
		updatedRichSteward.setBusinessId(1l);
		updatedRichSteward.setFirstName("Vasek");
		updatedRichSteward.setLastName("Havel");
		updatedRichSteward.setFlights(flights);
		
		mockStewardService();
		mockMappingService();
    }
	
	@Test
	public void getAllStewards() {
		// fire 
		Set<StewardSimpleDTO> returned = stewardFacade.getAllStewards();
		
		// assert
		assertThat(returned).containsExactly(stewardDTO);
		
		// verify mocks
		verify(stewardService).findAllStewards();
	}
	
    @Test
    public void createSteward() {
		// fire
		Long returnedStewardId = stewardFacade.createSteward(stewardCreateDTO);
		
		// assert
		assertThat(returnedStewardId).isEqualTo(1L);
		
		// verify mocks
		verify(stewardService).createSteward(Matchers.any(Steward.class));
    }
	
	@Test
	public void getSteward() {
		// fire
		StewardDTO returned = stewardFacade.getSteward(1l);
		
		// assert
		assertThat(returned).isEqualTo(stewardRichDTO);
		
		// verify mocks
		verify(stewardService).findSteward(B_ID);
	}
	
	@Test
	public void deleteSteward() {
		// fire
		stewardFacade.deleteSteward(1l);
		
		// verify mocks
		verify(stewardService).deleteSteward(Matchers.anyLong());
	}
	
	@Test
	public void updateNames() {
		// mock mapping
		Mockito.when(mappingService.mapTo(Matchers.any(), Matchers.eq(Steward.class)))
				.thenReturn(updatedSteward);
		Mockito.when(mappingService.mapTo(Matchers.any(), Matchers.eq(StewardDTO.class)))
				.thenReturn(updatedRichSteward);
		
		// fire
		//StewardDTO returned = stewardFacade.updateNames(1l, "Vasek", SURNAME);
		
		// verify correct updated steward is sent to the persistence layer
		verify(stewardService).updateSteward(updatedSteward);
	}
	
	@Test
	public void getAllFlightsForSteward() {
		// fire
		Set<FlightDTO> returned = stewardFacade.getAllFlightsForSteward(1l);
		
		// assert
		assertThat(returned).containsExactly(flight);
		
		// verify
		verify(stewardService).findSteward(1l);
	}
	
	private void mockStewardService() {
		// get all
		Mockito.when(stewardService.findAllStewards())
				.thenReturn(set);
		// create
		Mockito.when(stewardService.createSteward(Matchers.any(Steward.class)))
				.thenReturn(1L);
		// get
		Mockito.when(stewardService.findSteward(Matchers.anyLong()))
				.thenReturn(steward);
	}
	
	private void mockMappingService() {
		// mock mapping steward
		Mockito.when(mappingService.mapTo(Matchers.any(), Matchers.eq(Steward.class)))
				.thenReturn(steward);
		// mock mapping stewardDTO
		Mockito.when(mappingService.mapTo(Matchers.any(), Matchers.eq(StewardSimpleDTO.class)))
				.thenReturn(stewardDTO);
		// mock mapping StewardCreateDTO
		Mockito.when(mappingService.mapTo(Matchers.any(), Matchers.eq(StewardDTO.class)))
				.thenReturn(stewardRichDTO);
		// mock collection of stewardsDTO
		Mockito.when(mappingService.mapTo(Matchers.anyCollection(), Matchers.eq(StewardSimpleDTO.class)))
				.thenReturn(new HashSet<>(Arrays.asList(stewardDTO)));
	}
   

}

package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightSimpleDTO;
import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import cz.muni.fi.pa165.airport_manager.service.FlightService;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import cz.muni.fi.pa165.airport_manager.service.StewardService;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * Test class for {@link FlightFacadeImpl}
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightFacadeTest {

    @Mock
    private FlightService flightService;
    
    @Mock
    private StewardService stewardService;

    @Mock
    private MappingService mappingService;

    @InjectMocks
    private FlightFacadeImpl flightFacade = new FlightFacadeImpl();

    private final Long testId = 123l;
    private Steward steward;
    private Destination from;
    private Destination to;
    private Flight flight;

    @Before
    public void setUp() {

        from = new Destination("CGN", "Köln", "Deutschland");
        to = new Destination("DUS", "Düsseldorf", "Deutschland");

        steward = new Steward(123l, "Peter", "Pan", new HashSet<Flight>());

        Set<Steward> stewards = new HashSet<>();
        stewards.add(steward);

        Airplane airplane = new Airplane("Boing", AirplaneType.ECONOMY.name(), 150);
        flight = new Flight(Boolean.TRUE, new Date(10000l), new Date(20000l), stewards, airplane, from, to);
        flight.setId(testId);
    }

    @Test
    public void create() {
        FlightCreateDTO flightCreateDTO = new FlightCreateDTO();
        flightFacade.create(flightCreateDTO);

        verify(mappingService).mapTo(flightCreateDTO, Flight.class);
        verify(flightService).create(Matchers.any(Flight.class));
    }
    
    @Test
    public void update() {
        FlightSimpleDTO flightSimpleDTO = new FlightSimpleDTO();
        flightFacade.update(flightSimpleDTO);

        verify(mappingService).mapTo(flightSimpleDTO, Flight.class);
        //verify(flightService).update(Matchers.any(Flight.class));
    }
    
    @Test
    public void getFlight() {
        flightFacade.getFlight(testId);

        verify(flightService).findById(testId);
        verify(mappingService).mapTo(flightService.findById(testId), FlightDTO.class);
    }

    @Test
    public void deleteAirplaneById() {
        flightFacade.delete(testId);

        verify(flightService).delete(testId);
    }

    @Test
    public void getFlights() {
        flightFacade.getFlights();

        verify(flightService).findAll();
        verify(mappingService).mapTo(Matchers.anyCollectionOf(Flight.class),
                Matchers.eq(FlightSimpleDTO.class));
    }
   
    @Test
    public void addSteward() {
        flightFacade.addSteward(steward.getId(), flight.getId());
        
        verify(flightService).update(flight);
    }
    
    @Test
    public void removeSteward() {
        flightFacade.removeSteward(steward.getId(), flight.getId());
        
        verify(flightService).update(flight);
    }   
}

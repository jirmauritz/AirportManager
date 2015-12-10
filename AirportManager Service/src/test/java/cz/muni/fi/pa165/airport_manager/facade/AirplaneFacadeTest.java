package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.service.AirplaneService;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;


/**
 * Test class for {@link AirplaneFacadeImpl}
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
@RunWith(MockitoJUnitRunner.class)
public class AirplaneFacadeTest {

    @InjectMocks
    private AirplaneFacadeImpl airplaneFacade;
    @Mock
    private AirplaneService airplaneService;
    @Mock
    private MappingService mappingService;

    private final static Long TEST_ID = 1L;
    private final static int TEST_CAPACITY = 200;

    @Test
    public void createAirplaneTest() {
        AirplaneCreateDTO airplaneCreateDTO = new AirplaneCreateDTO();
        airplaneFacade.createAirplane(airplaneCreateDTO);
        verify(mappingService).mapTo(airplaneCreateDTO,  Airplane.class);
        verify(airplaneService).create(Matchers.any(Airplane.class));

    }

    @Test
    public void getAirplaneTest() {
        airplaneFacade.getAirplane(TEST_ID);
        verify(airplaneService).findById(TEST_ID);
        verify(mappingService).mapTo(airplaneService.findById(TEST_ID),  AirplaneDTO.class);
    }

    @Test(expected=NullPointerException.class)
    public void getAirplaneTestNull() {
        airplaneFacade.getAirplane(null);
    }

    @Test
    public void deleteAirplaneByIdTest() {
        airplaneFacade.deleteAirplaneById(TEST_ID);
        verify(airplaneService).delete(TEST_ID);
    }
    @Test(expected=NullPointerException.class)
    public void deleteAirplaneByIdTestNull() {
        airplaneFacade.deleteAirplaneById(null);
    }
    @Test
    public void findAllTest() {
        airplaneFacade.findAll();
        verify(airplaneService).findAll();
        verify(mappingService).mapTo(Matchers.anyCollectionOf(Airplane.class),  Matchers.eq(AirplaneDTO.class));
    }

    @Test
    public void findByTypeTest() {
        airplaneFacade.findByType("Business");
        verify(airplaneService).findByType("Business");
        verify(mappingService).mapTo(Matchers.anyCollectionOf(Airplane.class),  Matchers.eq(AirplaneDTO.class));
    }

    @Test(expected=NullPointerException.class)
    public void findByTypeTestNull() {
        airplaneFacade.findByType(null);
    }

    @Test
    public void findByMinCapacityTest() {
        airplaneFacade.findByMinCapacity(TEST_CAPACITY);
        verify(airplaneService).findByMinCapacity(TEST_CAPACITY);
        verify(mappingService).mapTo(Matchers.anyCollectionOf(Airplane.class),  Matchers.eq(AirplaneDTO.class));
    }
}

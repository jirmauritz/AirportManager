package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.DestinationCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.service.DestinationService;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class DestinationFacadeTest {

    private @Mock DestinationService destinationService;
    private @Mock MappingService mappingService;

    @InjectMocks
    private DestinationFacade destinationFacade =
            new DestinationFacadeImpl();

    private Destination destination;
    private DestinationCreateDTO destinationCreateDTO;
    private DestinationSimpleDTO destinationDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        destination = new Destination("Vaclav Havel Airport", "Prague", "Czech Republic");
        destinationCreateDTO = new DestinationCreateDTO();
        destinationCreateDTO.setName("Vaclav Havel Airport");
        destinationCreateDTO.setCity("Prague");
        destinationCreateDTO.setCountry("Czech Republic");
        destinationDTO = new DestinationSimpleDTO();
        destinationDTO.setId(1L);
        destinationDTO.setName("Vaclav Havel Airport");
        destinationDTO.setCity("Prague");
        destinationDTO.setCountry("Czech Republic");
    }

    @Test
    public void create() {
        this.callCreateDestinationOnDestinationFacade();

        verify(mappingService).mapTo(destinationCreateDTO, Destination.class);
        verify(destinationService).create(destination);
    }

    @Test
    public void delete() {
        this.callCreateDestinationOnDestinationFacade();

        doReturn(destination).when(destinationService).findById(1L);

        destinationFacade.delete(1L);

        verify(destinationService).findById(1L);
        verify(destinationService).delete(destination);
    }

    @Test
    public void findById() {
        destination.setId(1L);
        doReturn(destination).when(destinationService).findById(1L);
        doReturn(destinationDTO).when(mappingService).mapTo(destination, DestinationSimpleDTO.class);

        final DestinationSimpleDTO returnedDestination = destinationFacade.findById(1L);

        verify(destinationService).findById(1L);
        verify(mappingService).mapTo(destination, DestinationSimpleDTO.class);

        assertThat(returnedDestination)
                .isNotNull()
                .isEqualToComparingFieldByField(destinationDTO);
    }

    @Test
    public void findByAirportCode() {
        destination.setId(1L);
        doReturn(destination).when(destinationService).findByAirportCode("Vaclav Havel Airport");
        doReturn(destinationDTO).when(mappingService).mapTo(destination, DestinationSimpleDTO.class);

        final DestinationSimpleDTO returnedDestination = destinationFacade.findByAirportCode("Vaclav Havel Airport");

        verify(destinationService).findByAirportCode("Vaclav Havel Airport");
        verify(mappingService).mapTo(destination, DestinationSimpleDTO.class);

        assertThat(returnedDestination)
                .isNotNull()
                .isEqualToComparingFieldByField(destinationDTO);
    }

    @Test
    public void findAll() {
        final Destination destination1 = new Destination("Vaclav Havel Airport", "Prague", "Czech Republic");
        destination1.setId(1L);
        final Destination destination2 = new Destination("Some other Airport", "Brno", "Czech Republic");
        destination2.setId(2L);

        final Set<Destination> destinations = new HashSet<>();
        destinations.add(destination1);
        destinations.add(destination2);

        final DestinationSimpleDTO destinationDTO1 = new DestinationSimpleDTO();
        destinationDTO1.setId(1L);
        destinationDTO1.setName("Vaclav Havel Airport");
        destinationDTO1.setCity("Prague");
        destinationDTO1.setCountry("Czech Republic");

        final DestinationSimpleDTO destinationDTO2 = new DestinationSimpleDTO();
        destinationDTO2.setId(2L);
        destinationDTO2.setName("Some other Airport");
        destinationDTO2.setCity("Brno");
        destinationDTO2.setCountry("Czech Republic");

        final Set<DestinationSimpleDTO> destinationDTOs = new HashSet<>();
        destinationDTOs.add(destinationDTO1);
        destinationDTOs.add(destinationDTO2);

        doReturn(destinations).when(destinationService).findAll();
        doReturn(destinationDTOs).when(mappingService).mapTo(destinations, DestinationSimpleDTO.class);

        Set<DestinationSimpleDTO> returnedDestinations = destinationFacade.findAll();

        verify(destinationService).findAll();
        verify(mappingService).mapTo(destinations, DestinationSimpleDTO.class);

        assertThat(returnedDestinations)
                .isNotNull()
                .contains(destinationDTO1, destinationDTO2);
    }

    @Test
    public void findByCountry() {
        final Destination destination1 = new Destination("Vaclav Havel Airport", "Prague", "Czech Republic");
        destination1.setId(1L);
        final Destination destination2 = new Destination("Some other Airport", "Brno", "Czech Republic");
        destination2.setId(2L);

        final Set<Destination> destinations = new HashSet<>();
        destinations.add(destination1);
        destinations.add(destination2);

        final DestinationSimpleDTO destinationDTO1 = new DestinationSimpleDTO();
        destinationDTO1.setId(1L);
        destinationDTO1.setName("Vaclav Havel Airport");
        destinationDTO1.setCity("Prague");
        destinationDTO1.setCountry("Czech Republic");

        final DestinationSimpleDTO destinationDTO2 = new DestinationSimpleDTO();
        destinationDTO2.setId(2L);
        destinationDTO2.setName("Some other Airport");
        destinationDTO2.setCity("Brno");
        destinationDTO2.setCountry("Czech Republic");

        final Set<DestinationSimpleDTO> destinationDTOs = new HashSet<>();
        destinationDTOs.add(destinationDTO1);
        destinationDTOs.add(destinationDTO2);

        doReturn(destinations).when(destinationService).findByCountry("Czech Republic");
        doReturn(destinationDTOs).when(mappingService).mapTo(destinations, DestinationSimpleDTO.class);

        Set<DestinationSimpleDTO> returnedDestinations = destinationFacade.findByCountry("Czech Republic");

        verify(destinationService).findByCountry("Czech Republic");
        verify(mappingService).mapTo(destinations, DestinationSimpleDTO.class);

        assertThat(returnedDestinations)
                .isNotNull()
                .contains(destinationDTO1, destinationDTO2);
    }

    private void callCreateDestinationOnDestinationFacade() {
        doReturn(destination).when(mappingService).mapTo(destinationCreateDTO, Destination.class);
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Destination) invocation.getArguments()[0]).setId(1L);
                return null;
            }
        }).when(destinationService).create(destination);

        destinationFacade.create(destinationCreateDTO);
    }

}

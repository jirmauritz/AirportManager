package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.AirplaneDao;
import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import org.assertj.core.api.Condition;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.validator.internal.util.CollectionHelper.asSet;
import static org.mockito.Mockito.*;

/**
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */

@RunWith(MockitoJUnitRunner.class)
public class AirplaneServiceTest {
    private @Mock AirplaneDao airplaneDao;
    private @Mock FlightService flightService;

    @InjectMocks
    private AirplaneService airplaneService = new AirplaneServiceImpl();

    public @Rule ExpectedException expected = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        final Airplane airplane = newAirplane();

        final Long returnedAirplaneId = this.callCreateAirplaneOnAirplaneService(airplane);

        assertThat(returnedAirplaneId)
                .isNotNull()
                .isEqualTo(1L);

        verify(airplaneDao).create(airplane);
    }

    @Test(expected = NullPointerException.class)
    public void createNullAirplane() {
        airplaneService.create(null);
    }

    @Test
    public void createNegativeCapacity() {
        final Airplane airplane = newAirplane();
        airplane.setCapacity(-1);

        expected.expect(IllegalArgumentException.class);
        airplaneService.create(airplane);
    }

    @Test
    public void createNullName() {
        final Airplane airplane = newAirplane();
        airplane.setName(null);

        expected.expect(IllegalArgumentException.class);
        airplaneService.create(airplane);
    }

    @Test()
    public void createEmptyName() {
        final Airplane airplane = newAirplane();
        airplane.setName("");

        expected.expect(IllegalArgumentException.class);
        airplaneService.create(airplane);
    }

    @Test
    public void createNullType() {
        final Airplane airplane = newAirplane();
        airplane.setType(null);

        expected.expect(IllegalArgumentException.class);
        airplaneService.create(airplane);
    }

    @Test
    public void createEmptyType() {
        final Airplane airplane = newAirplane();
        airplane.setType("");

        expected.expect(IllegalArgumentException.class);
        airplaneService.create(airplane);
    }

    @Test
    public void update() {
        final Airplane airplane = newAirplane();

        this.callCreateAirplaneOnAirplaneService(airplane);

        airplane.setName("Aeroplan2");
        airplane.setType("Business");
        airplaneService.update(airplane);

        verify(airplaneDao).update(airplane);
    }

    @Test(expected = NullPointerException.class)
    public void updateNullAirplane() {
        airplaneService.update(null);
    }

    @Test
    public void updateNegativeCapacity() {
        final Airplane airplane = newAirplane();
        airplane.setId(1L);
        airplane.setCapacity(-1);

        expected.expect(IllegalArgumentException.class);
        airplaneService.update(airplane);
    }

    @Test
    public void updateNullName() {
        final Airplane airplane = newAirplane();
        airplane.setId(1L);
        airplane.setName(null);

        expected.expect(IllegalArgumentException.class);
        airplaneService.update(airplane);
    }

    @Test
    public void updateEmptyName() {
        final Airplane airplane = newAirplane();
        airplane.setId(1L);
        airplane.setName("");

        expected.expect(IllegalArgumentException.class);
        airplaneService.update(airplane);
    }

    @Test
    public void updateNullType() {
        final Airplane airplane = newAirplane();
        airplane.setId(1L);
        airplane.setType(null);

        expected.expect(IllegalArgumentException.class);
        airplaneService.update(airplane);
    }

    @Test
    public void updateEmptyType() {
        final Airplane airplane = newAirplane();
        airplane.setId(1L);
        airplane.setType("");

        expected.expect(IllegalArgumentException.class);
        airplaneService.update(airplane);
    }

    @Test
    public void delete() {
        final Airplane airplane = newAirplane();
        airplane.setId(1L);

        doReturn(airplane).when(airplaneDao).findById(1L);

        airplaneService.delete(airplane.getId());

        ArgumentCaptor<Airplane> captor = ArgumentCaptor.forClass(Airplane.class);
        verify(airplaneDao).delete(captor.capture());
        assertThat(captor.getValue())
                .isNotNull()
                .is(new Condition<Airplane>() {
                    @Override
                    public boolean matches(Airplane value) {
                        final Airplane onlyIdAirplane = new Airplane();
                        onlyIdAirplane.setId(1L);
                        return value.equals(airplane) || value.equals(onlyIdAirplane);
                    }
                });
    }

    @Test(expected = NullPointerException.class)
    public void deleteNull() {
        airplaneService.delete(null);
    }

    @Test
    public void findById() {
        final Airplane airplane = newAirplane();
        airplane.setId(1L);

        doReturn(airplane).when(airplaneDao).findById(1L);

        final Airplane returnedAirplane = airplaneService.findById(1L);

        verify(airplaneDao).findById(1L);
        assertThat(returnedAirplane)
                .isNotNull()
                .isEqualToComparingFieldByField(airplane);
    }

    @Test(expected = NullPointerException.class)
    public void findByIdNull() {
        airplaneService.findById(null);
    }

    @Test
    public void findAll() {
        final Airplane airplane1 = newAirplane();
        airplane1.setName("Plane1");
        final Airplane airplane2 = newAirplane();
        airplane2.setName("Plane2");
        final Airplane airplane3 = newAirplane();
        airplane3.setName("Plane3");
        airplane3.setType("Boeing");

        Set<Airplane> allPlanes = new HashSet<>();
        allPlanes.add(airplane1);
        allPlanes.add(airplane2);
        allPlanes.add(airplane3);

        doReturn(allPlanes).when(airplaneDao).findAll();

        final Set<Airplane> returnedAirplanes = airplaneService.findAll();

        assertThat(returnedAirplanes)
                .isNotNull()
                .containsAll(allPlanes);
    }

    @Test
    public void findAllEmptyCollection() {
        doReturn(Collections.EMPTY_SET).when(airplaneDao).findAll();

        final Set<Airplane> returnedAirplanes = airplaneService.findAll();

        verify(airplaneDao).findAll();

        assertThat(returnedAirplanes)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void findByType() {
        final Airplane airplane1 = newAirplane();
        airplane1.setName("Plane1");
        final Airplane airplane2 = newAirplane();
        airplane2.setName("Plane2");
        final Airplane airplane3 = newAirplane();
        airplane3.setName("Plane3");
        airplane3.setType("Boeing");

        Set<Airplane> allPlanesEconomy = new HashSet<>();
        allPlanesEconomy.add(airplane1);
        allPlanesEconomy.add(airplane2);
        Set<Airplane> allPlanesBusiness = new HashSet<>();
        allPlanesBusiness.add(airplane3);

        doReturn(allPlanesEconomy) .when(airplaneDao).findByType("Economy");
        doReturn(allPlanesBusiness).when(airplaneDao).findByType("Business");

        Set<Airplane> returnedAirplanes = airplaneService.findByType(AirplaneType.ECONOMY);

        verify(airplaneDao).findByType("Economy");

        assertThat(returnedAirplanes)
                .isNotNull()
                .containsAll(allPlanesEconomy);
    }

    @Test(expected = NullPointerException.class)
    public void findByTypeNull() {
        airplaneService.findByType(null);
    }

    @Test
    public void findByTypeEmptyCollection() {
        doReturn(Collections.EMPTY_SET).when(airplaneDao).findByType(any(String.class));

        final Set<Airplane> returnedAirplanes = airplaneService.findByType(AirplaneType.BUSINESS);

        verify(airplaneDao).findByType("Business");

        assertThat(returnedAirplanes)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void findByMinCapacity() {
        final Airplane airplane1 = newAirplane();
        airplane1.setName("Plane1");
        airplane1.setCapacity(50);
        final Airplane airplane2 = newAirplane();
        airplane2.setName("Plane2");
        airplane1.setCapacity(150);
        final Airplane airplane3 = newAirplane();
        airplane3.setName("Plane3");
        airplane1.setCapacity(250);

        doReturn(asSet(airplane1, airplane2, airplane3)).when(airplaneDao).findByMinCapacity(50);
        doReturn(asSet(airplane3)).when(airplaneDao).findByMinCapacity(200);

        Set<Airplane> returnedAirplanes50  = airplaneService.findByMinCapacity(50);
        Set<Airplane> returnedAirplanes200 = airplaneService.findByMinCapacity(200);

        assertThat(returnedAirplanes50)
                .isNotNull()
                .contains(airplane1, airplane2, airplane3);

        assertThat(returnedAirplanes200)
                .isNotNull()
                .contains(airplane3);
    }

    @Test
    public void findByMinCapacityEmptyCollection() {
        doReturn(Collections.EMPTY_SET).when(airplaneDao).findAll();

        final Set<Airplane> returnedAirplanes = airplaneService.findAll();

        verify(airplaneDao).findAll();

        assertThat(returnedAirplanes)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void isAvailableTrueNoFlights() {
        final Airplane airplane = newAirplane();
        airplane.setId(1L);

        final Date date1 = new Date(1420070400000L); // 2015-01-01 UTC
        final Date date2 = new Date(1422748800000L); // 2015-02-01 UTC

        doReturn(airplane).when(airplaneDao).findById(1L);
        doReturn(Collections.emptySet()).when(flightService).findFlightsInInterval(date1, date2);

        final Boolean isAvailable = airplaneService.isAvailable(1L, date1, date2);

        assertThat(isAvailable)
                .isNotNull()
                .isTrue();
    }

    @Test
    public void isAvailableTrueSomeFlights() {
        final Airplane airplane1 = newAirplane();
        airplane1.setId(1L);
        airplane1.setName("Plane1");
        final Airplane airplane2 = newAirplane();
        airplane2.setId(2L);
        airplane2.setName("Plane2");

        final Date date1 = new Date(1420070400000L); // 2015-01-01 UTC
        final Date date2 = new Date(1422748800000L); // 2015-02-01 UTC

        final Flight flight = new Flight(true, date1, date2, Collections.<Steward>emptySet(), airplane1, null, null);

        doReturn(airplane2).when(airplaneDao).findById(2L);
        doReturn(asSet(flight)).when(flightService).findFlightsInInterval(date1, date2);

        final Boolean isAvailable = airplaneService.isAvailable(2L, date1, date2);

        assertThat(isAvailable)
                .isNotNull()
                .isTrue();
    }

    @Test
    public void isAvailableFalse() {
        final Airplane airplane = newAirplane();
        airplane.setId(1L);

        final Date date1 = new Date(1420070400000L); // 2015-01-01 UTC
        final Date date2 = new Date(1422748800000L); // 2015-02-01 UTC
        final Date date2_1 = new Date(1422748800000L + 1000L); // after  2015-02-01 UTC
        final Date date2_2 = new Date(1425168000000L - 1000L); // before 2015-03-01 UTC
        final Date date3 = new Date(1425168000000L); // 2015-03-01 UTC
        final Date date4 = new Date(1427846400000L); // 2015-04-01 UTC

        final Flight flight1 = new Flight(true, date1, date2_1, Collections.<Steward>emptySet(), airplane, null, null);
        final Flight flight2 = new Flight(true, date2_2, date4, Collections.<Steward>emptySet(), airplane, null, null);

        doReturn(airplane).when(airplaneDao).findById(1L);
        doReturn(asSet(flight1, flight2)).when(flightService).findFlightsInInterval(date2, date3);

        final Boolean isAvailable = airplaneService.isAvailable(1L, date2, date3);

        assertThat(isAvailable)
                .isNotNull()
                .isFalse();

        verify(airplaneDao).findById(1L);
        verify(flightService).findFlightsInInterval(date2, date3);
    }

    @Test(expected = NullPointerException.class)
    public void isAvailableNull() {
        airplaneService.isAvailable(null, new Date(), new Date());
    }

    @Test(expected = IllegalArgumentException.class)
    public void isAvailableWrongTimes() {
        airplaneService.isAvailable(
                1L,
                new Date(System.currentTimeMillis() + 10000L),
                new Date(System.currentTimeMillis())
        );
    }

    @Test
    public void getAllAvailable() {
        final Airplane airplane1 = newAirplane();
        airplane1.setId(1L);
        airplane1.setName("Plane1");
        final Airplane airplane2 = newAirplane();
        airplane2.setId(2L);
        airplane2.setName("Plane2");
        final Airplane airplane3 = newAirplane();
        airplane3.setId(3L);
        airplane3.setName("Plane3");
        final Airplane airplane4 = newAirplane();
        airplane4.setId(4L);
        airplane4.setName("Plane4");

        final Date date1 = new Date(1420070400000L); // 2015-01-01 UTC
        final Date date2 = new Date(1422748800000L); // 2015-02-01 UTC
        final Date date3 = new Date(1422748800000L + 1000L); // after  2015-02-01 UTC
        final Date date4 = new Date(1425168000000L - 1000L); // before 2015-03-01 UTC
        final Date date5 = new Date(1425168000000L); // 2015-03-01 UTC

        final Flight flight1 = new Flight(true, date1, date3, Collections.<Steward>emptySet(), airplane1, null, null);
        final Flight flight2 = new Flight(true, date1, date5, Collections.<Steward>emptySet(), airplane3, null, null);

        doReturn(asSet(flight1, flight2)).when(flightService).findFlightsInInterval(date2, date4);
        doReturn(asSet(airplane1, airplane2, airplane3, airplane4)).when(airplaneDao).findAll();

        final Set<Airplane> returnedAirplanes = airplaneService.getAllAvailable(date2, date4);

        assertThat(returnedAirplanes)
                .isNotNull()
                .isNotEmpty()
                .contains(airplane2, airplane4);

        verify(flightService).findFlightsInInterval(date2, date4);
        verify(airplaneDao).findAll();
    }

    @Test
    public void getAllAvailableNoFlights() {
        final Airplane airplane1 = newAirplane();
        airplane1.setId(1L);
        airplane1.setName("Plane1");
        final Airplane airplane2 = newAirplane();
        airplane2.setId(2L);
        airplane2.setName("Plane2");

        doReturn(asSet(airplane1, airplane2)).when(airplaneDao).findAll();
        doReturn(Collections.emptySet()).when(flightService).findFlightsInInterval(any(Date.class), any(Date.class));

        final Set<Airplane> returnedAirplanes = airplaneService.getAllAvailable(
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis() + 10000L)
        );

        assertThat(returnedAirplanes)
                .isNotNull()
                .isNotEmpty()
                .contains(airplane1, airplane2);

        verify(airplaneDao).findAll();
        verify(flightService).findFlightsInInterval(any(Date.class), any(Date.class));
    }


    @Test(expected = IllegalArgumentException.class)
    public void getAllAvailableWrongTimes() {
        airplaneService.getAllAvailable(
                new Date(System.currentTimeMillis() + 10000L),
                new Date(System.currentTimeMillis())
        );
    }

    private Long callCreateAirplaneOnAirplaneService(Airplane airplane) {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Airplane) invocation.getArguments()[0]).setId(1L);
                return null;
            }
        }).when(airplaneDao).create(airplane);

        return airplaneService.create(airplane);
    }

    private static Airplane newAirplane() {
        return new Airplane("Airplane1", "Economy", 300);
    }
}

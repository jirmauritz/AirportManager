package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.config.ServiceConfiguration;
import cz.muni.fi.pa165.airport_manager.dao.AirplaneDao;
import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import org.assertj.core.api.Condition;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

/**
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class AirplaneServiceTest {
/*
    private @Mock AirplaneDao airplaneDao;

    @InjectMocks
    private AirplaneService airplaneService = new AirplaneServiceImpl();

    private @Rule ExpectedException expected;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        final Airplane airplane = newAirplane();

        this.callCreateAirplaneOnAirplaneService(airplane);

        verify(airplaneDao).create(airplane);
    }

    @Test(expected = NullPointerException.class)
    public void createNullAirplane() {
        airplaneService.create(null);

        verify(airplaneService, never()).create(any(Airplane.class));
    }

    @Test
    public void createNegativeCapacity() {
        final Airplane airplane = newAirplane();
        airplane.setCapacity(-1);

        expected.expect(IllegalArgumentException.class);
        airplaneService.create(airplane);

        verify(airplaneDao, never()).create(airplane);
    }

    @Test
    public void createNullName() {
        final Airplane airplane = newAirplane();
        airplane.setName(null);

        expected.expect(IllegalArgumentException.class);
        airplaneService.create(airplane);

        verify(airplaneDao, never()).create(airplane);
    }

    @Test
    public void createEmptyName() {
        final Airplane airplane = newAirplane();
        airplane.setName("");

        expected.expect(IllegalArgumentException.class);
        airplaneService.create(airplane);

        verify(airplaneDao, never()).create(airplane);
    }

    @Test
    public void createNullType() {
        final Airplane airplane = newAirplane();
        airplane.setType(null);

        expected.expect(IllegalArgumentException.class);
        airplaneService.create(airplane);

        verify(airplaneDao, never()).create(airplane);
    }

    @Test
    public void createEmptyType() {
        final Airplane airplane = newAirplane();
        airplane.setType("");

        expected.expect(IllegalArgumentException.class);
        airplaneService.create(airplane);

        verify(airplaneDao, never()).create(airplane);
    }

    @Test
    public void update() {
        final Airplane airplane = newAirplane();

        this.callCreateAirplaneOnAirplaneService(airplane);

        airplane.setName("Aeroplan2");
        airplane.setType("Boeing 737");
        airplaneService.update(airplane);

        verify(airplaneDao).update(airplane);
    }

    @Test(expected = NullPointerException.class)
    public void updateNullAirplane() {
        airplaneService.update(null);

        verify(airplaneDao, never()).update(any(Airplane.class));
    }

    @Test
    public void updateNegativeCapacity() {
        final Airplane airplane = newAirplane();
        airplane.setCapacity(-1);

        expected.expect(IllegalArgumentException.class);
        airplaneService.update(airplane);

        verify(airplaneDao, never()).update(airplane);
    }

    @Test
    public void updateNullName() {
        final Airplane airplane = newAirplane();
        airplane.setName(null);

        expected.expect(IllegalArgumentException.class);
        airplaneService.update(airplane);

        verify(airplaneDao, never()).update(airplane);
    }

    @Test
    public void updateEmptyName() {
        final Airplane airplane = newAirplane();
        airplane.setName("");

        expected.expect(IllegalArgumentException.class);
        airplaneService.update(airplane);

        verify(airplaneDao, never()).update(airplane);
    }

    @Test
    public void updateNullType() {
        final Airplane airplane = newAirplane();
        airplane.setType(null);

        expected.expect(IllegalArgumentException.class);
        airplaneService.update(airplane);

        verify(airplaneDao, never()).update(airplane);
    }

    @Test
    public void updateEmptyType() {
        final Airplane airplane = newAirplane();
        airplane.setType("");

        expected.expect(IllegalArgumentException.class);
        airplaneService.update(airplane);

        verify(airplaneDao, never()).update(airplane);
    }

    @Test
    public void delete() {
        final Airplane airplane = newAirplane();

        doReturn(airplane).when(airplaneDao).findById(airplane.getId());

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

        verify(airplaneDao, never()).delete(any(Airplane.class));
    }

    @Test
    public void findById() {
        final Airplane airplane = newAirplane();

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

        verify(airplaneDao, never()).findById(any(Long.class));
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

        final SortedSet<Airplane> returnedAirplanes = airplaneService.findAll();

        assertThat(returnedAirplanes)
                .isNotNull()
                .containsAll(allPlanes);
    }

    @Test
    public void findAllEmptyCollection() {
        doReturn(Collections.EMPTY_SET).when(airplaneDao).findAll();

        final SortedSet<Airplane> returnedAirplanes = airplaneService.findAll();

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

        SortedSet<Airplane> returnedAirplanes = airplaneService.findByType(AirplaneType.ECONOMY);

        verify(airplaneDao).findByType("Economy");

        assertThat(returnedAirplanes)
                .isNotNull()
                .containsAll(allPlanesEconomy);
    }

    @Test(expected = NullPointerException.class)
    public void findByTypeNull() {
        airplaneService.findByType(null);

        verify(airplaneDao, never()).findByType(any(String.class));
    }

    @Test
    public void findByTypeEmptyCollection() {
        doReturn(Collections.EMPTY_SET).when(airplaneDao).findByType(any(String.class));

        final SortedSet<Airplane> returnedAirplanes = airplaneService.findByType(AirplaneType.BUSINESS);

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

        Set<Airplane> allPlanes = new HashSet<>();
        allPlanes.add(airplane1);
        allPlanes.add(airplane2);
        allPlanes.add(airplane3);

        doReturn(allPlanes).when(airplaneDao).findAll();

        SortedSet<Airplane> returnedAirplanes100 = airplaneService.findByMinCapacity(100);
        SortedSet<Airplane> returnedAirplanes200 = airplaneService.findByMinCapacity(200);

        verify(airplaneDao, times(2)).findAll();

        assertThat(returnedAirplanes100)
                .isNotNull()
                .contains(airplane2, airplane3);

        assertThat(returnedAirplanes200)
                .isNotNull()
                .contains(airplane3);
    }

    @Test
    public void findByMinCapacityEmptyCollection() {
        doReturn(Collections.EMPTY_SET).when(airplaneDao).findAll();

        final SortedSet<Airplane> returnedAirplanes = airplaneService.findAll();

        verify(airplaneDao).findAll();

        assertThat(returnedAirplanes)
                .isNotNull()
                .isEmpty();
    }

    private void callCreateAirplaneOnAirplaneService(Airplane airplane) {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Airplane) invocation.getArguments()[0]).setId(1L);
                return null;
            }
        }).when(airplaneDao).create(airplane);

        airplaneService.create(airplane);
    }

    private static Airplane newAirplane() {
        return new Airplane("Airplane1", "Economy", 300);
    }
*/
}

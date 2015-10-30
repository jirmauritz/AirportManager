package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.EmbeddedPersistenceContext;
import cz.muni.fi.pa165.entity.Airplane;
import cz.muni.fi.pa165.entity.Destination;
import cz.muni.fi.pa165.entity.Flight;
import cz.muni.fi.pa165.entity.Steward;
import org.assertj.core.api.ThrowableAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;


import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.Ignore;

/**
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedPersistenceContext.class)
@Ignore
public class FlightDaoImplTest {

    private @Autowired FlightDao flightDao;
    private @PersistenceContext EntityManager em;

    public @Rule ExpectedException expected = ExpectedException.none();

    // 2015-01-01T00:00Z[UTC]
    private static final long time1 = 1420070400000L;
    // 2015-01-01T12:00Z[UTC]
    private static final long time2 = 1420113600000L;
    // 2015-02-03T01:00Z[UTC]
    private static final long time3 = 1422925200000L;
    // 2015-02-03T03:00Z[UTC]
    private static final long time4 = 1422932400000L;

    private Airplane newAirplane = null;
    private Destination newTo = null;
    private Destination newFrom = null;

    @Test
    public void create() {
        final Flight newFlight = createFlight(time1, time2);
        flightDao.create(newFlight);

        final Flight savedFlight = em.find(Flight.class, newFlight.getId());

        assertThat(savedFlight)
                .isNotNull()
                .isEqualTo(newFlight);
    }

    @Test(expected = NullPointerException.class)
    public void createNullFlight() {
        flightDao.create(null);

//        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
//            @Override
//            public void call() throws Throwable {
//                flightDao.create(null);
//            }
//        })
//                .isInstanceOf(NullPointerException.class);

//        assertThatThrownBy(() -> flightDao.create(null))
//                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void createDepartureAfterArrival() {
        final Flight newFlight = createFlight(time2, time1);

        expected.expect(Exception.class);
        flightDao.create(newFlight);
    }

    @Test
    public void update() {
        final Flight newFlight = createFlight(time1, time3);
        em.persist(newFlight);

        final Destination newTo = new Destination("name", "city", "country");
        newFlight.setTo(newTo);
        em.persist(newTo);

        flightDao.update(newFlight);

        final Flight savedFlight = em.find(Flight.class, newFlight.getId());

        assertThat(savedFlight)
                .isNotNull()
                .isEqualTo(newFlight)
                .withFailMessage("Returned Flight is not updated");

        assertThat(savedFlight.getTo())
                .isNotNull()
                .isEqualTo(newTo)
                .withFailMessage("Returned Flight does not have correct Destination 'to' set");
    }

    @Test(expected = NullPointerException.class)
    public void updateNullFlight() {
        flightDao.update(null);
    }


    @Test
    public void delete() {
        final Flight newFlight = createFlight(time3, time4);
        em.persist(newFlight);

        flightDao.delete(newFlight);

        assertThat(em.find(Flight.class, newFlight.getId()))
                .isNull();
    }

    @Test(expected = NullPointerException.class)
    public void deleteNullFlight() {
        flightDao.delete(null);
    }

    @Test
    public void find() {
        final Flight newFlight = createFlight(time3, time4);
        em.persist(newFlight);

        final Flight savedFlight = flightDao.findById(newFlight.getId());

        assertThat(savedFlight)
                .isNotNull()
                .isEqualTo(newFlight)
                .withFailMessage("Returned Flight is not the same");
    }

    @Test(expected = NullPointerException.class)
    public void findNullId() {
        flightDao.findById(null);
    }

    @Test
    public void findAll() {
        final Flight flight1 = createFlight(time1, time2);
        flight1.setInternational(true);
        em.persist(flight1);
        final Flight flight2 = createFlight(time2, time3);
        flight2.setInternational(false);
        em.persist(flight2);
        final Flight flight3 = createFlight(time2, time4);
        flight3.setInternational(true);
        em.persist(flight3);
        final Flight flight4 = createFlight(time3, time4);
        flight4.setInternational(true);
        em.persist(flight4);


        final Set<Flight> savedFlights = flightDao.findAll();

        assertThat(savedFlights)
                .isNotNull()
                .hasOnlyElementsOfType(Flight.class)
                .hasSize(4)
                .hasSameElementsAs(asList(flight1, flight2, flight3, flight4))
                .withFailMessage("Returned flights are not the same as saved ones");
    }

    @Test
    public void findAllInternational() {
        final Flight flight1 = createFlight(time1, time2);
        flight1.setInternational(true);
        em.persist(flight1);
        final Flight flight2 = createFlight(time2, time3);
        flight2.setInternational(false);
        em.persist(flight2);
        final Flight flight3 = createFlight(time3, time4);
        flight3.setInternational(true);
        em.persist(flight3);

        final Set<Flight> savedFlights = flightDao.findAllInternational();

        assertThat(savedFlights)
                .isNotNull()
                .hasSize(2)
                .hasSameElementsAs(asList(flight1, flight3))
                .withFailMessage("Returned flights are missing some elements or contains unexpected ones");
    }

    @Test
    public void findByDeparture() {
        final Flight flight1 = createFlight(time1, time2);
        em.persist(flight1);
        final Flight flight2 = createFlight(time2, time3);
        em.persist(flight2);
        final Flight flight3 = createFlight(time1, time4);
        em.persist(flight3);

        final Set<Flight> savedFlights = flightDao.findByDeparture(new Date(time1));

        assertThat(savedFlights)
                .isNotNull()
                .hasSize(2)
                .hasSameElementsAs(asList(flight1, flight3))
                .withFailMessage("Returned flights are missing some elements or contains unexpected ones");
    }

    @Test
    public void findByArrival() {
        final Flight flight1 = createFlight(time1, time2);
        em.persist(flight1);
        final Flight flight2 = createFlight(time2, time3);
        em.persist(flight2);
        final Flight flight3 = createFlight(time1, time3);
        em.persist(flight3);

        final Set<Flight> savedFlights = flightDao.findByArrival(new Date(time3));

        assertThat(savedFlights)
                .isNotNull()
                .hasSize(2)
                .hasSameElementsAs(asList(flight2, flight3))
                .withFailMessage("Returned flights are missing some elements or contains unexpected ones");
    }

    @Test
    public void findByFromDestination() {
        final Destination from1 = new Destination("nameFrom1", "cityFrom1", "countryFrom1");
        em.persist(from1);
        final Destination from2 = new Destination("nameFrom2", "cityFrom2", "countryFrom2");
        em.persist(from2);
        final Flight flight1 = createFlight(time1, time2);
        flight1.setFrom(from1);
        em.persist(flight1);
        final Flight flight2 = createFlight(time2, time3);
        flight2.setFrom(from2);
        em.persist(flight2);
        final Flight flight3 = createFlight(time3, time4);
        flight3.setFrom(from1);
        em.persist(flight3);

        final Set<Flight> savedFlights = flightDao.findAllFromDestination(from1);

        assertThat(savedFlights)
                .isNotNull()
                .hasSize(2)
                .hasSameElementsAs(asList(flight1, flight3))
                .withFailMessage("Returned flights are missing some elements or contains unexpected ones");
    }

    @Test
    public void findByToDestination() {
        final Destination to1 = new Destination("nameTo1", "cityTo1", "countryTo1");
        em.persist(to1);
        final Destination to2 = new Destination("nameTo2", "cityTo2", "countryTo2");
        em.persist(to2);
        final Flight flight1 = createFlight(time1, time2);
        flight1.setTo(to1);
        em.persist(flight1);
        final Flight flight2 = createFlight(time2, time3);
        flight2.setTo(to2);
        em.persist(flight2);
        final Flight flight3 = createFlight(time3, time4);
        flight3.setTo(to1);
        em.persist(flight3);

        final Set<Flight> savedFlights = flightDao.findAllToDestination(to2);

        assertThat(savedFlights)
                .isNotNull()
                .hasSize(1)
                .hasSameElementsAs(Collections.singletonList(flight2))
                .withFailMessage("Returned flights are missing some elements or contains unexpected ones");
    }


    
    private Flight createFlight(final long departure, final long arrival) {
        if (newAirplane == null) {
            newAirplane = new Airplane(
                    "must not be null 0",
                    "must not be null 0",
                    0
            );
            em.persist(newAirplane);
        }
        if (newTo == null) {
            newTo = new Destination(
                    "must not be null 1",
                    "must not be null 1",
                    "must not be null 1"
            );
            em.persist(newTo);
        }
        if (newFrom == null) {
            newFrom = new Destination(
                    "must not be null 2",
                    "must not be null 2",
                    "must not be null 2"
            );
            em.persist(newFrom);
        }

        final Flight flight = new Flight();
        flight.setId(null);
        flight.setInternational(false);
        flight.setDeparture(new Date(departure));
        flight.setArrival(new Date(arrival));
        flight.setStewards(Collections.<Steward>emptySet());
        flight.setAirplane(newAirplane);
        flight.setFrom(newFrom);
        flight.setTo(newTo);

        return flight;
    }
}

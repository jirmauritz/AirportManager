package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.EmbeddedPersistenceContext;
import cz.muni.fi.pa165.entity.Airplane;
import cz.muni.fi.pa165.entity.Destination;
import cz.muni.fi.pa165.entity.Flight;
import cz.muni.fi.pa165.entity.Steward;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmbeddedPersistenceContext.class)
public class FlightDaoImplTest {

    // 2015-01-01T00:00Z[UTC]
    private static final long time1 = 1420070400000L;
    // 2015-01-01T12:00Z[UTC]
    private static final long time2 = 1420113600000L;
    // 2015-02-03T01:00Z[UTC]
    private static final long time3 = 1422925200000L;
    // 2015-02-03T03:00Z[UTC]
    private static final long time4 = 1422932400000L;

//    @Inject
//    private FlightDaoImpl flightDao;

    @Inject
    private EntityManager em;

    @Test
    public void test() {
    }

//    @Test
//    public void testCreate() {
//        final Flight newFlight = createFlight(time1, time2);
//        flightDao.create(newFlight);
//
//        final Flight savedFlight = em.find(Flight.class, newFlight.getId());
//
//        assertThat(savedFlight)
//                .isNotNull()
//                .isEqualTo(newFlight);
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testCreateNullFlight() {
//        flightDao.create(null);
//    }

    private Flight createFlight(final long departure, final long arrival) {
        return new Flight(
                null,
                false,
                new Date(departure),
                new Date(arrival),
                Collections.<Steward>emptyList(),
                new Airplane(),
                new Destination(),
                new Destination()
        );
    }

}

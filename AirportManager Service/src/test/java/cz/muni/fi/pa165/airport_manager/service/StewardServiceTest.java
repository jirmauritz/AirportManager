package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.StewardDao;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.exception.DataAccessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * /**
 * Test class for {@link StewardServiceImpl}
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
@RunWith(MockitoJUnitRunner.class)
public class StewardServiceTest {

    @InjectMocks
    private StewardServiceImpl stewardService;

    @Mock
    private StewardDao stewardDao;


    private Steward steward = new Steward();
    private Steward steward1 = new Steward();
    private Steward createSteward = new Steward();

    private static final Long TEST_ID = 1L;
    private static final String TEST_NAME = "Test Name";
    private static final String TEST_NAME1 = "Test name 1";
    private static final Long TEST_ID1 = 2L;

    private Date from = new Date(4000);
    private Date to = new Date(10000);

    private Set<Steward> stewards = new HashSet<Steward>();
    private Set<Flight> flights1 = new HashSet<Flight>();

    @Before
    public void setUp() {
        createSteward.setFirstName(TEST_NAME);
        createSteward.setLastName(TEST_NAME1);

        steward.setId(TEST_ID);
        steward.setBusinessId(TEST_ID1);
        steward.setFirstName(TEST_NAME);
        steward.setLastName(TEST_NAME);

        steward1.setBusinessId(TEST_ID);
        steward1.setFirstName(TEST_NAME1);
        steward1.setLastName(TEST_NAME1);

        Flight flight = new Flight();
        flight.setDeparture(new Date(4000));
        flight.setArrival(new Date(7000));
        Set<Flight> flights = new HashSet<Flight>();
        flights.add(flight);

        Flight flight1 = new Flight();
        flight1.setDeparture(new Date(10000));
        flight1.setArrival(new Date(15000));
        flights1.add(flight1);
        steward.setFlights(flights);
        steward1.setFlights(flights1);

        stewards.add(steward);
        stewards.add(steward1);

        initMock();
    }

    private void initMock() {
        when(stewardDao.findAll()).thenReturn(stewards);
        when(stewardDao.findById(TEST_ID)).thenReturn(steward);
    }

    //has id set
    @Test(expected = IllegalStateException.class)
    public void createStewardTestException() {
        createSteward.setId(TEST_ID);
        stewardService.createSteward(createSteward);
    }

    //has businessId set
    @Test(expected = IllegalStateException.class)
    public void createStewardTestException2() {
        createSteward.setBusinessId(TEST_ID);
        stewardService.createSteward(createSteward);
    }

    // has not first name set
    @Test(expected = IllegalStateException.class)
    public void createStewardTestException3() {
        createSteward.setFirstName(null);
        stewardService.createSteward(createSteward);
    }

    //has not last name set
    @Test(expected = IllegalStateException.class)
    public void createStewardTestException4() {
        createSteward.setLastName(null);
        stewardService.createSteward(createSteward);
    }

    //has flights set
    @Test(expected = IllegalStateException.class)
    public void createStewardTestException5() {
        createSteward.setFlights(flights1);
        stewardService.createSteward(createSteward);
    }

    @Test(expected = NullPointerException.class)
    public void createStewardTestException6() {
        stewardService.createSteward(null);
    }

    @Test(expected = DataAccessException.class)
    public void createTestException() {
        doThrow(PersistenceException.class).when(stewardDao).create(createSteward);
        stewardService.createSteward(createSteward);
    }

    @Test
    public void createStewardTest() {
        stewardService.createSteward(createSteward);
        verify(stewardDao).create(createSteward);
    }

    @Test(expected = NullPointerException.class)
    public void findStewardTestException() {
        stewardService.findSteward(null);
    }

    @Test(expected = DataAccessException.class)
    public void findStewardTestException1() {
        doThrow(PersistenceException.class).when(stewardDao).findById(TEST_ID);
        stewardService.findSteward(TEST_ID);
    }

    @Test
    public void findStewardTest() {
        stewardService.findSteward(TEST_ID);
        verify(stewardDao).findById(TEST_ID);
    }

    @Test(expected = DataAccessException.class)
    public void findAllTestException() {
        when(stewardDao.findAll()).thenThrow(PersistenceException.class);
        stewardService.findAllStewards();
    }

    @Test
    public void findAllStewardsTest() {
        stewardService.findAllStewards();
        verify(stewardDao).findAll();
    }

    //has businessId change because dao.findById always return steward
    @Test(expected = IllegalStateException.class)
    public void updateStewardTestException() {
        steward1.setId(TEST_ID);
        stewardService.updateSteward(steward1);
    }

    // has first name null
    @Test(expected = IllegalStateException.class)
    public void updateStewardTestException2() {
        steward.setFirstName(null);
        stewardService.updateSteward(steward);
    }

    // has last name null
    @Test(expected = IllegalStateException.class)
    public void updateStewardTestException3() {
        steward.setLastName(null);
        stewardService.updateSteward(steward);
    }

    @Test(expected = NullPointerException.class)
    public void updateStewardTestException4() {
        stewardService.updateSteward(null);
    }

    //has not id set
    @Test(expected = NullPointerException.class)
    public void updateStewardTestException5() {
        stewardService.updateSteward(steward1);

    }


    @Test(expected = DataAccessException.class)
    public void updateTestException() {
        doThrow(PersistenceException.class).when(stewardDao).update(steward);
        stewardService.updateSteward(steward);
    }

    @Test
    public void updateStewardTest() {
        stewardService.updateSteward(steward);
        verify(stewardDao).update(steward);
    }

    @Test(expected = NullPointerException.class)
    public void deleteStewardTestException() {
        stewardService.deleteSteward(null);

    }

    @Test(expected = DataAccessException.class)
    public void deleteStewardTestException1() {
        doThrow(PersistenceException.class).when(stewardDao).delete(steward);
        stewardService.deleteSteward(TEST_ID);
    }

    @Test
    public void deleteStewardTest() {
        stewardService.deleteSteward(TEST_ID);
        verify(stewardDao).delete(steward);
    }

    @Test(expected = NullPointerException.class)
    public void isAvailableTestException() {
        stewardService.isAvailable(null, from, to);

    }

    @Test(expected = NullPointerException.class)
    public void isAvailableTestException1() {
        stewardService.isAvailable(TEST_ID, null, to);

    }

    @Test(expected = NullPointerException.class)
    public void isAvailableTestException2() {
        stewardService.isAvailable(TEST_ID, from, null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void isAvailableTestException3() {
        stewardService.isAvailable(TEST_ID, to, from);
    }

    @Test
    public void isAvailableTest() {

        //Steward's flight starts inside of interval and ends after interval
        boolean first = stewardService.isAvailable(TEST_ID, new Date(3000), new Date(6000));
        Assert.assertFalse(first);

        //Steward's flight starts before interval and ends inside of interval
        boolean second = stewardService.isAvailable(TEST_ID, new Date(6000), new Date(8000));
        Assert.assertFalse(second);

        //Steward's flight starts before interval and ends after interval
        boolean third = stewardService.isAvailable(TEST_ID, new Date(5000), new Date(6000));
        Assert.assertFalse(third);

        //Steward's flight starts and ends inside of interval
        boolean fourth = stewardService.isAvailable(TEST_ID, new Date(3000), new Date(8000));
        Assert.assertFalse(fourth);

        //Steward's flight starts after interval
        boolean fifth = stewardService.isAvailable(TEST_ID, new Date(2000), new Date(3000));
        Assert.assertTrue(fifth);

        //Steward's flight starts before interval
        boolean sixth = stewardService.isAvailable(TEST_ID, new Date(8000), new Date(10000));
        Assert.assertTrue(sixth);
    }

    @Test(expected = NullPointerException.class)
    public void getAllAvailableTestException() {
        stewardService.getAllAvailable(null, to);

    }

    @Test(expected = NullPointerException.class)
    public void getAllAvailableTestException1() {
        stewardService.getAllAvailable(from, null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void getAllAvailableTestException2() {
        stewardService.getAllAvailable(to, from);
    }

    @Test
    public void getAllAvailableTest() {

        //Interval does not interfere to flights of stewards.
        Set<Steward> availableStewards = stewardService.getAllAvailable(new Date(8000), new Date(9000));
        assertThat(availableStewards).contains(steward,steward1);

        //Interval interferes to steward's flight;
        Set<Steward> availableStewards1 = stewardService.getAllAvailable(new Date(5000), new Date(6000));
        assertThat(availableStewards1).contains(steward1);

        //Interval interferes to flights of both stewards.
        Set<Steward> availableStewards2 = stewardService.getAllAvailable(new Date(5000), new Date(20000));
        assertThat(availableStewards2).isNotNull().isEmpty();
    }

}

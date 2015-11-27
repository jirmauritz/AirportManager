package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.StewardDao;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.exception.DataAccessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 /**
 * Test class for {@link StewardServiceImpl}
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
@RunWith(MockitoJUnitRunner.class)
public class StewardServiceTest {

    private static final Long TEST_ID = 1L;
    private static final String TEST_NAME = "Test Name";
    private static final String TEST_NAME1 = "Test name 1";
    private static final Long TEST_ID1 = 2L;

    @InjectMocks
    private StewardServiceImpl stewardService;

    @Mock
    private StewardDao stewardDao;

    private Steward steward = new Steward();
    private Steward steward1 = new Steward();


    private Date from = new Date(400l);
    private Date to = new Date(1000l);

    @Before
    public void setUp(){
        steward.setId(TEST_ID);
        steward.setBusinessId(TEST_ID1);
        steward.setFirstName(TEST_NAME);
        steward.setLastName(TEST_NAME);

        steward1.setFirstName(TEST_NAME1);
        steward1.setLastName(TEST_NAME1);

        Flight flight = new Flight();
        flight.setDeparture(new Date(4001));
        flight.setArrival(new Date(7001));
        Set<Flight> flights = new HashSet<Flight>();
        flights.add(flight);

        steward.setFlights(flights);
        initMock();
    }

    private void initMock(){
        when(stewardDao.findById(TEST_ID)).thenReturn(steward);
    }

    @Test(expected = IllegalStateException.class)
    public void createStewardTestException() {
        stewardService.createSteward(steward);
    }

    @Test(expected = IllegalStateException.class)
    public void createStewardTestException2() {
        steward.setId(null);
        stewardService.createSteward(steward);
    }

    @Test(expected = IllegalStateException.class)
    public void createStewardTestException3() {
        steward.setFirstName(null);
        stewardService.createSteward(steward);
    }

    @Test(expected = IllegalStateException.class)
    public void createStewardTestException4() {
        steward.setLastName(null);
        stewardService.createSteward(steward);
    }

    @Test(expected = NullPointerException.class)
    public void createStewardTestException5() {
        stewardService.createSteward(null);
    }

    @Test(expected = DataAccessException.class)
    public void createTestException(){
        doThrow(PersistenceException.class).when(stewardDao).create(steward1);
        stewardService.createSteward(steward1);
    }
    @Test
    @Ignore
    public void createStewardTest(){
        //waiting for edit
    }

    @Test(expected = NullPointerException.class)
    public void findStewardTestException(){
        stewardService.findSteward(null);
    }
    @Test(expected = DataAccessException.class)
    public void findStewardTestException1(){
        doThrow(PersistenceException.class).when(stewardDao).findById(TEST_ID);
        stewardService.findSteward(TEST_ID);
    }

    @Test
    public void findStewardTest(){
        stewardService.findSteward(TEST_ID);
        verify(stewardDao).findById(TEST_ID);
    }

    @Test(expected = DataAccessException.class)
    public void findAllTestException(){
        when(stewardDao.findAll()).thenThrow(PersistenceException.class);
        stewardService.findAllStewards();
    }

    @Test
    public void findAllStewardsTest(){
        stewardService.findAllStewards();
        verify(stewardDao).findAll();
    }


    @Test(expected = IllegalStateException.class)
    public void updateStewardTestException() {
        steward1.setId(TEST_ID);
        stewardService.updateSteward(steward1);
    }


    @Test(expected = IllegalStateException.class)
    public void updateStewardTestException2() {
        steward.setFirstName(null);
        stewardService.updateSteward(steward);
    }

    @Test(expected = IllegalStateException.class)
    public void updateStewardTestException3() {
        steward.setLastName(null);
        stewardService.updateSteward(steward);
    }

    @Test(expected = NullPointerException.class)
    public void updateStewardTestException4() {
        stewardService.updateSteward(null);
    }

    @Test(expected = NullPointerException.class)
    public void updateStewardTestException5() {
        stewardService.updateSteward(steward1);

    }


    @Test(expected = DataAccessException.class)
    public void updateTestException(){
        doThrow(PersistenceException.class).when(stewardDao).update(steward);
        stewardService.updateSteward(steward);
    }

    @Test
    public void updateStewardTest(){
        stewardService.updateSteward(steward);
        verify(stewardDao).update(steward);
    }

    @Test(expected = NullPointerException.class)
    public void deleteStewardTestException() {
        stewardService.deleteSteward(null);

    }

    @Test(expected = DataAccessException.class)
    public void deleteStewardTestException1(){
        doThrow(PersistenceException.class).when(stewardDao).delete(steward);
        stewardService.deleteSteward(TEST_ID);
    }

    @Test
    public void deleteStewardTest(){
        stewardService.deleteSteward(TEST_ID);
        verify(stewardDao).delete(steward);
    }

    @Test(expected = NullPointerException.class)
    public void isAvailableTestException() {
        stewardService.isAvailable(null,from,to);

    }
    @Test(expected = NullPointerException.class)
    public void isAvailableTestException1() {
        stewardService.isAvailable(TEST_ID,null,to);

    }
    @Test(expected = NullPointerException.class)
    public void isAvailableTestException2() {
        stewardService.isAvailable(TEST_ID,from,null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void isAvailableTestException3() {
        stewardService.isAvailable(TEST_ID,to,from);

    }
    @Test
    public void isAvailableTest() {

        //Steward's flight starts inside of interval and ends after interval
        boolean first = stewardService.isAvailable(TEST_ID, new Date(3001), new Date(6001));
        Assert.assertFalse(first);

        //Steward's flight starts before interval and ends inside of interval
        boolean second = stewardService.isAvailable(TEST_ID, new Date(6001), new Date(8001));
        Assert.assertFalse(second);

        //Steward's flight starts before interval and ends after interval
        boolean third = stewardService.isAvailable(TEST_ID, new Date(5001), new Date(6001));
        Assert.assertFalse(third);

        //Steward's flight starts and ends inside of interval
        boolean fourth = stewardService.isAvailable(TEST_ID, new Date(3001), new Date(8001));
        Assert.assertFalse(fourth);

        //Steward's flight starts after interval
        boolean fifth = stewardService.isAvailable(TEST_ID, new Date(2001), new Date(3001));
        Assert.assertTrue(fifth);

        //Steward's flight starts before interval
        boolean sixth = stewardService.isAvailable(TEST_ID, new Date(8001), new Date(10001));
        Assert.assertTrue(sixth);
    }
/*

    @Override
    public Set<Steward> getAllAvailable(final Date from, final Date to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        if (!to.after(from)) {
            throw new IllegalArgumentException("Invalid time range.");
        }

        final Set<Steward> allStewards = new HashSet<>(this.findAllStewards());

        final Iterator<Steward> i = allStewards.iterator();
        while (i.hasNext()) {
            Steward s = i.next();
            if (!this.isAvailable(s, from, to)){
                i.remove();
            }
        }

        return allStewards;
    }

*/


}

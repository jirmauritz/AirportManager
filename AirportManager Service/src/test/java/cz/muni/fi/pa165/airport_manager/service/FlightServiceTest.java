package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.FlightDao;
import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {

	private @Mock
	FlightDao flightDao;

	@InjectMocks
	private FlightService flightService
			= new FlightServiceImpl();

	// objects used
	private Flight flight;
	private Destination from;
	private Destination to;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		Steward steward = new Steward(1l, "Vaclav", "Havel", new HashSet<Flight>());
		Set<Steward> stewards = new HashSet<>();
		stewards.add(steward);
		Airplane airplane = new Airplane("Boing", AirplaneType.BUSINESS.name(), 200);
		from = new Destination("KEF", "Reykjavik", "Iceland");
		to = new Destination("VIE", "Vienna", "Austria");
		flight = new Flight(true, new Date(1000l), new Date(2000l), stewards, airplane, from, to);

		mockFlightDao();
	}

	// ----------------- create flight -----------------------
	@Test
	public void create() {
		// fire
		flightService.create(flight);

		// verify mocks
		verify(flightDao).create(Matchers.eq(flight));
	}

	@Test(expected = NullPointerException.class)
	public void createWithNull() {
		// fire
		flightService.create(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createWithIdAssigned() {
		// id set
		flight.setId(1l);

		// fire
		flightService.create(flight);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createWithNullInternational() {
		// null international
		flight.setInternational(null);

		// fire
		flightService.create(flight);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createWithNullStewards() {
		// null stewards
		flight.setStewards(null);

		// fire
		flightService.create(flight);
	}

	// ----------------- update flight ---------------------
	@Test
	public void update() {
		// set id
		flight.setId(1l);

		// mock
		Mockito.when(flightDao.findById(Matchers.anyLong()))
				.thenReturn(flight);

		// fire
		flightService.update(flight);

		// verify
		verify(flightDao).update(flight);
	}

	@Test(expected = NullPointerException.class)
	public void updateNullObject() {
		// fire
		flightService.update(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateWithNullId() {
		// null id
		flight.setId(null);

		// fire
		flightService.update(flight);
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateWithNullInternational() {
		// null international
		flight.setInternational(null);

		// fire
		flightService.update(flight);
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateWithNullStewards() {
		// null stewards
		flight.setStewards(null);

		// fire
		flightService.update(flight);
	}

	// ----------------- delete flight ------------------
	@Test
	public void delete() {
		// fire
		flightService.delete(1l);

		// verify
		verify(flightDao).delete(flight);
	}

	@Test(expected = NullPointerException.class)
	public void deleteNull() {
		// fire
		flightService.delete(null);
	}

	@Test
	public void deleteNonExisting() {
		// fire
		flightService.delete(1l);
		// ok
		// verify mock
		verify(flightDao).delete(flight);
	}

	@Test
	public void findById() {
		// fire 
		Flight returned = flightService.findById(1l);

		// assert
		assertThat(returned)
				.isNotNull()
				.isEqualTo(flight);

		// verify
		verify(flightDao).findById(1l);
	}

	@Test(expected = NullPointerException.class)
	public void findByNullId() {
		// fire
		flightService.findById(null);
	}

	@Test
	public void notFindById() {
		// mock findById
		Mockito.when(flightDao.findById(Matchers.anyLong()))
				.thenReturn(null);

		// fire
		Flight returned = flightService.findById(1l);

		// assert
		assertThat(returned).isNull();

		// verify
		verify(flightDao).findById(1l);
	}

	@Test
	public void findAll() {
		// fire
		Set<Flight> returned = flightService.findAll();

		// assert
		assertThat(returned)
				.isNotNull()
				.contains(flight);

		// verify
		verify(flightDao).findAll();
	}

	@Test
	public void findNone() {
		// mock findAll
		Set<Flight> flights = new HashSet<>();
		Mockito.when(flightDao.findAll())
				.thenReturn(flights);

		// fire
		Set<Flight> returned = flightService.findAll();

		// assert
		assertThat(returned)
				.isNotNull()
				.isEmpty();

		// verify
		verify(flightDao).findAll();
	}

	@Test
	public void findByFromDestination() {
		// fire
		Set<Flight> returned = flightService.findByFromDestination(from);

		// assert
		assertThat(returned)
				.isNotNull()
				.contains(flight);

		// verify
		verify(flightDao).findAllFromDestination(from);
	}

	@Test(expected = NullPointerException.class)
	public void findByNullFromDestination() {
		// fire 
		flightService.findByFromDestination(null);
	}

	@Test
	public void findByToDestination() {
		// fire
		Set<Flight> returned = flightService.findByToDestination(from);

		// assert
		assertThat(returned)
				.isNotNull()
				.contains(flight);

		// verify
		verify(flightDao).findAllToDestination(from);
	}

	@Test(expected = NullPointerException.class)
	public void findByNullToDestination() {
		// fire 
		flightService.findByToDestination(null);
	}

	@Test
	/*
	 * interval: |
	 * flight:   &
	 * relation: --|---|--&---&--
	 */
	public void findFlightsInInterval1() {
		// flight interval: 1000 - 2000
		// set interval
		Date fromInt = new Date(500l);
		Date toInt = new Date(700l);

		// fire
		Set<Flight> returned = flightService.findFlightsInInterval(fromInt, toInt);

		// assert nothing was found
		assertThat(returned)
				.isNotNull()
				.isEmpty();

		// verify
		verify(flightDao).findAll();
	}

	@Test
	/*
	 * interval: |
	 * flight:   &
	 * relation: --|-----&--|--&--
	 */
	public void findFlightsInInterval2() {
		// flight interval: 1000 - 2000
		// set interval
		Date fromInt = new Date(500l);
		Date toInt = new Date(1500l);

		// fire
		Set<Flight> returned = flightService.findFlightsInInterval(fromInt, toInt);

		// assert flight was found
		assertThat(returned)
				.isNotNull()
				.contains(flight);

		// verify
		verify(flightDao).findAll();
	}

	@Test
	/*
	 * interval: |
	 * flight:   &
	 * relation: ---&-|---|-&--
	 */
	public void findFlightsInInterval3() {
		// flight interval: 1000 - 2000
		// set interval
		Date fromInt = new Date(1300l);
		Date toInt = new Date(1700l);

		// fire
		Set<Flight> returned = flightService.findFlightsInInterval(fromInt, toInt);

		// assert flight was found
		assertThat(returned)
				.isNotNull()
				.contains(flight);

		// verify
		verify(flightDao).findAll();
	}
	
	@Test
	/*
	 * interval: |
	 * flight:   &
	 * relation: ---&--|---&--|--
	 */
	public void findFlightsInInterval4() {
		// flight interval: 1000 - 2000
		// set interval
		Date fromInt = new Date(1500l);
		Date toInt = new Date(2200l);

		// fire
		Set<Flight> returned = flightService.findFlightsInInterval(fromInt, toInt);

		// assert flight was found
		assertThat(returned)
				.isNotNull()
				.contains(flight);

		// verify
		verify(flightDao).findAll();
	}
	
	@Test
	/*
	 * interval: |
	 * flight:   &
	 * relation: ---&----&--|-----|---
	 */
	public void findFlightsInInterval5() {
		// flight interval: 1000 - 2000
		// set interval
		Date fromInt = new Date(2100l);
		Date toInt = new Date(2500l);

		// fire
		Set<Flight> returned = flightService.findFlightsInInterval(fromInt, toInt);

		// assert nothing was found
		assertThat(returned)
				.isNotNull()
				.isEmpty();

		// verify
		verify(flightDao).findAll();
	}
	
	@Test
	/*
	 * interval: |
	 * flight:   &
	 * relation: --|---&----&--|--
	 */
	public void findFlightsInInterval6() {
		// flight interval: 1000 - 2000
		// set interval
		Date fromInt = new Date(500l);
		Date toInt = new Date(2500l);

		// fire
		Set<Flight> returned = flightService.findFlightsInInterval(fromInt, toInt);

		// assert flight was found
		assertThat(returned)
				.isNotNull()
				.contains(flight);

		// verify
		verify(flightDao).findAll();
	}
	
	@Test(expected = NullPointerException.class)
	public void findFlightsInIntervalWithNullFrom() {
		// set interval
		Date fromInt = null;
		Date toInt = new Date(2500l);

		// fire
		flightService.findFlightsInInterval(fromInt, toInt);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void findFlightsInWrongInterval() {
		// set interval
		Date fromInt = new Date(2100l);
		Date toInt = new Date(2000l);

		// fire
		flightService.findFlightsInInterval(fromInt, toInt);
	}
	
	@Test
	/*
	 * interval: |
	 * flight:   &
	 * relation: ---&----&|-----|---
	 */
	public void findFlightsInTightInterval() {
		// flight interval: 1000 - 2000
		// set interval
		Date fromInt = new Date(2000l);
		Date toInt = new Date(2500l);

		// fire
		Set<Flight> returned = flightService.findFlightsInInterval(fromInt, toInt);

		// assert nothing was found
		assertThat(returned)
				.isNotNull()
				.isEmpty();

		// verify
		verify(flightDao).findAll();
	}
	
	@Test
	/*
	 * interval: |
	 * flight:   &
	 * relation: ---|----|&-----&---
	 */
	public void findFlightsInTightInterval2() {
		// flight interval: 1000 - 2000
		// set interval
		Date fromInt = new Date(500l);
		Date toInt = new Date(1000l);

		// fire
		Set<Flight> returned = flightService.findFlightsInInterval(fromInt, toInt);

		// assert nothing was found
		assertThat(returned)
				.isNotNull()
				.isEmpty();

		// verify
		verify(flightDao).findAll();
	}
	
	@Test(expected = NullPointerException.class)
	public void findFlightsInIntervalWithNullTo() {
		// set interval
		Date fromInt = new Date(2500l);
		Date toInt = null;

		// fire
		flightService.findFlightsInInterval(fromInt, toInt);
	}

	private void mockFlightDao() {
		// mock findById
		Mockito.when(flightDao.findById(Matchers.anyLong()))
				.thenReturn(flight);

		// mock findAll
		Set<Flight> flights = new HashSet<>();
		flights.add(flight);
		Mockito.when(flightDao.findAll())
				.thenReturn(flights);

		// mock from
		Mockito.when(flightDao.findAllFromDestination(Matchers.any(Destination.class)))
				.thenReturn(flights);

		// mock to
		Mockito.when(flightDao.findAllToDestination(Matchers.any(Destination.class)))
				.thenReturn(flights);
	}
}

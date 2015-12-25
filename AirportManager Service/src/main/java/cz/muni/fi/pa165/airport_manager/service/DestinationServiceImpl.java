package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.DestinationDao;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.exception.AirportManagerDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation of the (@link DestinationService}. This class uses
 * destinationDao to access the persistence.
 *
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */
@Service
public class DestinationServiceImpl implements DestinationService {

	@Autowired
	private DestinationDao destinationDao;

	@Autowired
	private FlightService  flightService;


	@Override
	public Long create(Destination destination) {
		verifyDestinationObject(destination);

		if (destination.getId() != null) {
			throw new IllegalArgumentException("Destination must not have id set.");
		}

		try {
			destinationDao.create(destination);
		} catch (Exception e) {
			throw new AirportManagerDataAccessException("Exception in persistence layer", e);
		}
		return destination.getId();
	}

	@Override
	public void update(Destination destination) {
		verifyDestinationObject(destination);

		if (destination.getId() == null) {
			throw new IllegalArgumentException("Destination must have id set.");
		}

		try {
			destinationDao.update(destination);
		} catch (Exception e) {
			throw new AirportManagerDataAccessException("Exception in persistence layer", e);
		}
	}

	@Override
	public void delete(Long id) {
		Objects.requireNonNull(id);
		try {
			destinationDao.delete(findById(id));
		} catch (Exception e) {
			throw new AirportManagerDataAccessException("Exception in persistence layer", e);
		}
	}

	@Override
	public Destination findById(Long id) {
		Objects.requireNonNull(id);
		try {
			return destinationDao.findById(id);
		} catch (Exception e) {
			throw new AirportManagerDataAccessException("Exception in persistence layer", e);
		}
	}

	@Override
	public Destination findByAirportCode(String code) {
		Objects.requireNonNull(code);
		try {
			return destinationDao.findByName(code);
		} catch (Exception e) {
			throw new AirportManagerDataAccessException("Exception in persistence layer", e);
		}
	}

	@Override
	public Set<Destination> findByCountry(String country) {
		Objects.requireNonNull(country);
		try {
			return destinationDao.findByCountry(country);
		} catch (Exception e) {
			throw new AirportManagerDataAccessException("Exception in persistence layer", e);
		}
	}

	@Override
	public Set<Destination> findAll() {
		try {
			return destinationDao.findAll();
		} catch (Exception e) {
			throw new AirportManagerDataAccessException("Exception in persistence layer", e);
		}
	}

	private void verifyDestinationObject(Destination destination) {
		Objects.requireNonNull(destination);

		if ((destination.getName() == null) || (destination.getName().isEmpty())) {
			throw new IllegalArgumentException("Destination name cannot be empty.");
		}

		if ((destination.getCity() == null) || (destination.getCity().isEmpty())) {
			throw new IllegalArgumentException("Destination city cannot be empty.");
		}

		if ((destination.getCountry() == null) || (destination.getCountry().isEmpty())) {
			throw new IllegalArgumentException("Destination country cannot be empty.");
		}
	}

	@Override
	public Set<Flight> getFlightsByDestinations (Long destinationId){
		Set<Flight> wantedFlights = new HashSet<>();
		for (Flight flight : flightService.findAll()){
			if (flight.getFrom().getId().equals(destinationId) || flight.getTo().getId().equals(destinationId)){
				wantedFlights.add(flight);
			}
		}
		return wantedFlights;
	}

}

package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.FlightDao;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.exception.AirportManagerDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Service implementation for FlightService.
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDao flightDao;

    @Override
    public Long create(Flight flight) {
        Objects.requireNonNull(flight);
        if (flight.getId() != null) {
            throw new IllegalArgumentException("Id cannot be set.");
        }
        if (isValid(flight)) {
            try {
                flightDao.create(flight);
            } catch (Exception e) {
                throw new AirportManagerDataAccessException("This exception was thrown while creating flight.", e);
            }
        }
		return flight.getId();
    }

    @Override
    public void update(Flight flight) {
        Objects.requireNonNull(flight);
        if (flight.getId() == null) {
            throw new IllegalArgumentException("Id must be set.");
        }
        Flight oldFlight = flightDao.findById(flight.getId());
        if (oldFlight == null) {
            throw new IllegalArgumentException("Flight with this id does not exist.");
        }
        try {
            flightDao.update(flight);
        } catch (Exception e) {
            throw new AirportManagerDataAccessException("This exception was thrown while updating flight.", e);
        }
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id);
        try {
            flightDao.delete(flightDao.findById(id));
        } catch (Exception e) {
            throw new AirportManagerDataAccessException("This exception was thrown while deleting flight.", e);
        }
    }

    @Override
    public Flight findById(Long id) {
        Objects.requireNonNull(id);
        try {
            return flightDao.findById(id);
        } catch (Exception e) {
            throw new AirportManagerDataAccessException("This exception was thrown while finding flight by given id.", e);
        }
    }

    @Override
    public Set<Flight> findAll() {
        try {
            return flightDao.findAll();
        } catch (Exception e) {
            throw new AirportManagerDataAccessException("This exception was thrown while finding all flights.", e);
        }
    }


    @Override
    public Set<Flight> findByFromDestination(Destination dest) {
        Objects.requireNonNull(dest);
        try {
            return flightDao.findAllFromDestination(dest);
        } catch (Exception e) {
            throw new AirportManagerDataAccessException("This exception was thrown while finding flight by given from-destination.", e);
        }
    }

    @Override
    public Set<Flight> findByToDestination(Destination dest) {
        Objects.requireNonNull(dest);
        try {
            return flightDao.findAllToDestination(dest);
        } catch (Exception e) {
            throw new AirportManagerDataAccessException("This exception was thrown while finding flight by given to-destination.", e);
        }
    }

    @Override
    public Set<Flight> findFlightsInInterval(Date from, Date to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        if (to.before(from)) {
            throw new IllegalArgumentException("From Date must be before to Date");
        }

        Set<Flight> allFlights = null;
        try {
            allFlights = flightDao.findAll();
        } catch (Exception e) {
            throw new AirportManagerDataAccessException("This exception was thrown while finding all flights in findFlightsInInterval method.", e);
        }
        Set<Flight> wantedFlights = new HashSet<Flight>();
        for (Flight flight : allFlights) {
            if (from.before(flight.getArrival()) && to.after(flight.getDeparture())) {
                wantedFlights.add(flight);
            }
        }
        return wantedFlights;
    }

        private boolean isValid (Flight flight){
            if (flight.getAirplane() == null ) {
                throw new IllegalArgumentException("Airplane cannot be null");
            }
            if (flight.getArrival() == null) {
                throw new IllegalArgumentException("Arrival cannot be null");
            }
            if (flight.getDeparture() == null) {
                throw new IllegalArgumentException("Departure cannot be null");
            }
            if (flight.getFrom() == null) {
                throw new IllegalArgumentException("From cannot be null");
            }
            if (flight.getTo() == null) {
                throw new IllegalArgumentException("To cannot be null");
            }
            if (flight.isInternational() == null) {
                throw new IllegalArgumentException("International cannot be null");
            }
            if (flight.getStewards() == null){
                throw new IllegalArgumentException("Flights cannot be null");
            }
            return true;


    }
}

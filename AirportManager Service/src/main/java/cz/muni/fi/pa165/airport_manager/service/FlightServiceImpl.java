package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.dao.FlightDao;
import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
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
    public void create(Flight flight) {
        Objects.requireNonNull(flight);
        if (flight.getId() != null){
            throw new IllegalArgumentException("Id cannot be set");
        }
            flightDao.create(flight);
    }

    @Override
    public void update(Flight flight) {
        Objects.requireNonNull(flight);
        if (flight.getId() == null){
            throw new IllegalArgumentException("Id must be set");
        }
        Flight oldFlight = flightDao.findById(flight.getId());
        flight.setStewards(oldFlight.getStewards());
        flightDao.update(flight);
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id);
        flightDao.delete(flightDao.findById(id));
    }

    @Override
    public Flight findById(Long id) {
        Objects.requireNonNull(id);
        return flightDao.findById(id);
    }

    @Override
    public Set<Flight> findAll() {
        return flightDao.findAll();
    }


    @Override
    public Set<Flight> findByFromDestination(Destination dest) {
        Objects.requireNonNull(dest);
        return flightDao.findAllFromDestination(dest);
    }

    @Override
    public Set<Flight> findByToDestination(Destination dest) {
        Objects.requireNonNull(dest);
        return flightDao.findAllToDestination(dest);
    }

    @Override
    public Set<Flight> findFlightsInInterval(Date from, Date to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        if (to.before(from)){
            throw new IllegalArgumentException("From Date must be before to Date");
        }

        Set<Flight> allFlights = flightDao.findAll();
        Set<Flight> wantedFlights = new HashSet<Flight>();
        for (Flight flight : allFlights){
            if (from.before(flight.getArrival()) && to.before(flight.getDeparture())){
                wantedFlights.add(flight);
            }
        }
        return wantedFlights;
    }
}

package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.dao.AirplaneDao;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import cz.muni.fi.pa165.airport_manager.exception.DataAccessException;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of Airplane service
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneDao airplaneDao;
    private FlightService flightService;

    @Override
    public Long create(Airplane airplane) {
        Objects.requireNonNull(airplane);
        
        if (airplane.getId() != null) {
            throw new IllegalStateException("Airplane cannot have id in it's "
                    + "current state.");
        }
        if ((airplane.getName() == null) || (airplane.getName().equals(""))) {
            throw new IllegalStateException("Airplane has no name.");
        }
        if (airplane.getCapacity() < 0) {
            throw new IllegalStateException("Airplane cannot have capacity less"
                    + " than 0.");
        }
        if ((airplane.getType() == null) || (!AirplaneType.isMember(airplane.getType()))) {
            throw new IllegalStateException("Invalid airplane type.");
        }
       
        try {
            airplaneDao.create(airplane);
        } catch (Exception e) {
            throw new DataAccessException("Error while persisting entity.", e);
        }

        return airplane.getId();
    }

    @Override
    public void update(Airplane airplane) {
        Objects.requireNonNull(airplane);
        Objects.requireNonNull(airplane.getId());
        
        if ((airplane.getName() == null) || (airplane.getName().equals(""))) {
            throw new IllegalStateException("Airplane has no name.");
        }
        if (airplane.getCapacity() < 0) {
            throw new IllegalStateException("Airplane cannot have capacity less "
                    + "than 0.");
        }
        if ((airplane.getType() == null) || (!AirplaneType.isMember(airplane.getType()))) {
            throw new IllegalStateException("Invalid airplane type.");
        }
        
        try {
            airplaneDao.update(airplane);
        } catch (Exception e) {
            throw new DataAccessException("Error while persisting entity.", e);
        }
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id);
        
        try {
            airplaneDao.delete(this.findById(id));
        } catch (Exception e) {
            throw new DataAccessException("Couldn't delete airplane with id " + id + ".", e);
        }
    }
    
    @Override
    public Airplane findById(Long id) {
        Objects.requireNonNull(id);
        try {
            return airplaneDao.findById(id);
        } catch (Exception e) {
            throw new DataAccessException("Exception on persitence layer.", e);
        }
    }

    @Override
    public Set<Airplane> findAll() {
        try {
            return airplaneDao.findAll();
        } catch (Exception e) {
            throw new DataAccessException("Exception on persitence layer.", e);
        }
    }

    @Override
    public Set<Airplane> findByType(AirplaneType type) {
        Objects.requireNonNull(type);
        if (!AirplaneType.isMember(type.toString())) {
            throw new IllegalArgumentException("No airplane of type : " +  type);  
        }
        
        try {
            return airplaneDao.findByType(type.toString());
        } catch (Exception e) {
            throw new DataAccessException("Exception on persitence layer.", e);
        }
    }

    @Override
    public Set<Airplane> findByMinCapacity(int minCapacity) {
        Objects.requireNonNull(minCapacity);
        if (minCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be less than 0.");
        }
        
        try {
            return airplaneDao.findByMinCapacity(minCapacity);
        } catch (Exception e) {
            throw new DataAccessException("Exception on persitence layer.", e);
        }
    }

    @Override
    public boolean isAvailable(Long id, final Date from, final Date to) {
        Objects.requireNonNull(id);
        
        // Check if the time range is valid
        if (!to.after(from)) {
            throw new IllegalArgumentException("Invalid time range.");
        }
        
        // Find entered airplane
        Airplane airplane = airplaneDao.findById(id);
        
        if (airplane == null) {
            throw new IllegalArgumentException("Airplane with id : " + id + "not found.");
        }
        
        // Find all flights in time range
        Set<Flight> allFlights = flightService.findFlightsInInterval(from, to);
        
        if (allFlights.isEmpty()) {
            return true;
        }
        
        // Check if airplane belongs to any flight already in time range
        for(Flight flight : allFlights) {
            if (flight.getAirplane().equals(airplane)) {
                return false;
            }
        }
                
        return true;     
    }

    @Override
    public Set<Airplane> getAllAvailable(final Date from, final Date to) {
        // Chech if the time range is valid
        if (!to.after(from)) {
            throw new IllegalArgumentException("Invalid time range.");
        }

        // Find all flights in time range
        Set<Flight> allFlights = flightService.findFlightsInInterval(from, to);
        
        /* Set of all available airplanes contains all airplanes excluding
        those, which are in allFlights. */
        Set<Airplane> allAirplanes = airplaneDao.findAll();
        
        if (allFlights.isEmpty()) {
            return allAirplanes;
        }

        Set<Airplane> allAvailableAirplanes = Collections.<Airplane>emptySet();
        allAvailableAirplanes.addAll(allAirplanes);
        
        for (Airplane airplane : allAirplanes) {
            for (Flight flight : allFlights) {
                if (flight.getAirplane().equals(airplane)) {
                    allAvailableAirplanes.remove(airplane);
                }
            }
        }
        
        return allAvailableAirplanes;
    }
}

package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.dao.AirplaneDao;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
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
    public Airplane create(Airplane airplane) {
        Objects.requireNonNull(airplane);
        
        if (airplane.getId() != null) {
            throw new IllegalStateException("Airplane cannot have id in it's "
                    + "current state.");
        }
        if (airplane.getName() == null) {
            throw new IllegalStateException("Airplane has no name.");
        }
        if (airplane.getCapacity() < 0) {
            throw new IllegalStateException("Airplane cannot have capacity less than 0");
        }
        if (airplane.getType() == null) {
            throw new IllegalStateException("Airplane has no type");
        }
       
        airplaneDao.create(airplane);
        airplane.setId(airplane.getId());
        airplaneDao.update(airplane);

        return this.findById(airplane.getId());
    }

    @Override
    public Airplane update(Airplane airplane) {
        Objects.requireNonNull(airplane);
        Objects.requireNonNull(airplane.getId());
        
        final Airplane persistedAirplane = airplaneDao.findById(airplane.getId());
        
        if (!airplane.equals(persistedAirplane)) {
            throw new IllegalStateException("Airplanes id was changed.");
        }
        if (airplane.getName() == null) {
            throw new IllegalStateException("Airplane has no name.");
        }
        if (airplane.getCapacity() < 0) {
            throw new IllegalStateException("Airplane cannot have capacity less than 0");
        }
        if (airplane.getType() == null) {
            throw new IllegalStateException("Airplane has no type");
        }
        
        airplaneDao.update(airplane);
        
        return this.findById(airplane.getId());
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id);
        Airplane toDelete = airplaneDao.findById(id);
        if (toDelete == null) {
            throw new IllegalArgumentException("Airplane with id : " + id + "not found.");
        }
        airplaneDao.delete(toDelete);
    }
    
    public Airplane findById(Long id) {
        Objects.requireNonNull(id);
        return airplaneDao.findById(id);
    }

    public Set<Airplane> findAll() {
        return airplaneDao.findAll();
    }

    public Set<Airplane> findByType(AirplaneType type) {
        Objects.requireNonNull(type);
        if (!AirplaneType.isMember(type.toString())) {
            throw new IllegalArgumentException("No airplane of type : " +  type);  
        }
        return airplaneDao.findByType(type.toString());
    }

    public Set<Airplane> findByMinCapacity(int minCapacity) {
        Objects.requireNonNull(minCapacity);
        if (minCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be less than 0.");
        }
        return airplaneDao.findByMinCapacity(minCapacity);
    }

    public boolean isAvailable(Long id, final Date from, final Date to) {
        Objects.requireNonNull(id);
        
        // Chech if the time range is valid
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

    public Set<Airplane> getAllAvailable(final Date from, final Date to) {
        // Chech if the time range is valid
        if (!to.after(from)) {
            throw new IllegalArgumentException("Invalid time range.");
        }

        // Find all flights in time range
        Set<Flight> allFlights = flightService.findFlightsInInterval(from, to);

        if (allFlights.isEmpty()) {
            return Collections.<Airplane>emptySet();
        }
        
        /* Set of all available airplanes contains all airplanes excluding
        those, which are in allFlights. */
        Set<Airplane> allAirplanes = airplaneDao.findAll();
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

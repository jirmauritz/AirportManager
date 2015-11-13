package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

/**
 * Service for the entity {@link Flight}
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
@Service
public interface FlightService {
    /**
     * Creates new entity.
     *
     * @param flight entity
     */
    void create(Flight flight);

    /**
     * Updates existed entity.
     *
     * @param flight entity to update
     */
    void update(Flight flight);

    /**
     * Deletes the entity identified by id.
     *
     * @param id of entity to delete
     */
    void delete(Long id);

    /**
     * Finds and returns entity by the specified id. 
     *
     * @param id
     * @return Flight with specified id or null
     */
    Flight findById(Long id);

    /**
     * Finds and returns all flights.
     *
     * @return Set containing all flights or empty Set
     */
    Set<Flight> findAll();

    /**
     * Finds and returns all international flights from the database.
     *
     * @return set of all the international flights
     */
    Set<Flight> findByInternational();

    /**
     * Finds all flights starting at the specified time.
     *
     * @param departure date
     * @return set of flights
     */
    Set<Flight> findByDeparture(Date departure);

    /**
     * Finds all flights ending at the specified time.
     *
     * @param arrival date
     * @return set of flights
     */
    Set<Flight> findByArrival(Date arrival);

    /**
     * Finds and returns all flights that departure from specified destination.
     *
     * @param dest destination
     * @return set of all flights departing from dest
     */
    Set<Flight> findByFromDestination(Destination dest);

    /**
     * Finds and returns all flights that arrive to specified destination.
     *
     * @param dest destination
     * @return set of all flights arriving to dest
     */
    Set<Flight> findByToDestination(Destination dest);

}

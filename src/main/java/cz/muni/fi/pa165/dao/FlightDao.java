package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Destination;
import cz.muni.fi.pa165.entity.Flight;
import java.util.Date;

import java.util.Set;

/**
 * DAO object to manage Flight objects.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */
public interface FlightDao {

    /**
     * Persists specified flight in the database.
     *
     * @param flight entity to persist
	 * @throws NullPointerException if the specified entity is null
     */
    void create(Flight flight) throws NullPointerException;

    /**
     * Updates the state of the specified entity in the database. The entity must have attribute id set.
     *
     * @param flight entity to update
     * @throws NullPointerException if the specified entity is null
     */
    void update(Flight flight) throws NullPointerException;

    /**
     * Deletes the specified entity from the database. The entity must have attribute id set.
     *
     * @param flight entity to delete
     * @throws NullPointerException if the specified entity is null
     */
    void delete(Flight flight) throws NullPointerException;
	
	/**
     * Finds and returns entity from the database by the specified id. If there is no such entity, null is returned.
     *
     * @param id an id to look for
     * @return Flight with specified id or null
     * @throws NullPointerException if the specified id is null
     */
    Flight findById(Long id) throws NullPointerException;

    /**
     * Finds and returns all flights from the database. If there are no flights, empty collection is returned.
     *
     * @return Set containing all flights or empty Set
     */
    Set<Flight> findAll();
	
	/**
	 * Finds and returns all international flights from the database. If there are no such flights, empty collection is returned.
	 * 
	 * @return set of all the international flights
	 */
	Set<Flight> findAllInternational();
	
	/**
	 * Finds all flights starting at the specified time. If there are no such flights, empty collection is returned.
	 * 
	 * @param departure date
	 * @return set of flights
	 */
	Set<Flight> findByDeparture(Date departure);
	
	/**
	 * Finds all flights ending at the specified time. If there are no such flights, empty collection is returned.
	 * 
	 * @param arrival date
	 * @return set of flights
	 */
	Set<Flight> findByArrival(Date arrival);
	
	/**
	 * Finds and returns all flights that departure from specified destination. If there are no such flights, empty collection is returned.
	 * 
	 * @param dest destination
	 * @return set of all flights departing from dest
	 */
	Set<Flight> findForDepartureDestination(Destination dest);
	
	/**
	 * Finds and returns all flights that arrive to specified destination. If there are no such flights, empty collection is returned.
	 * 
	 * @param dest destination
	 * @return set of all flights arriving to dest
	 */
	Set<Flight> findForArrivalDestination(Destination dest);

}

package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.entity.Destination;
import cz.muni.fi.pa165.airport_manager.entity.Flight;

import java.util.Set;

/**
 * Interface defining service methods over the {@link Destination} entity.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */
public interface DestinationService {
	 /**
     * Create new entity and persist it
     *
     * @param destination destination entity to create
	 * @throws IllegalArgumentException when the destination has id set
	 * @throws AirportManagerDataAccessException when a database failure occurs
	 * @return id
     */
    Long create(Destination destination);
	
	  /**
     * Update destination in database
     *
     * @param destination destination entity to update
	 * @throws IllegalArgumentException when the destination id is null
	 * @throws AirportManagerDataAccessException when a database failure occurs
     */
    void update(Destination destination);

    /**
     * Delete destination from database
     *
     * @param id destination id to delete
	 * @throws AirportManagerDataAccessException when a database failure occurs
     */
    void delete(Long id);	

    /**
     * Find by id
     *
	 * @param id - id of destination
	 * @throws AirportManagerDataAccessException when a database failure occurs
     * @return set of destinations
     */
    Destination findById(Long id);

	/**
     * Find by airport code.
     *
	 * @param code airport code
	 * * @throws AirportManagerDataAccessException when a database failure occurs
     * @return set of destinations
     */
    Destination findByAirportCode(String code);
	
    /**
     * Find all destinations in specified country
     *
	 * @param country - country of destination
	 * @throws AirportManagerDataAccessException when a database failure occurs
     * @return set of destination identifiers
     */
    Set<Destination> findByCountry(String country);

    /**
     * Find all
     *
     * @return set of destinations
	 * @throws AirportManagerDataAccessException when a database failure occurs
     */
    Set<Destination> findAll();


    /**
     * Find all flights assign to any destination
     *
	 * @throws AirportManagerDataAccessException when a database failure occurs
     * @return set of destinations
     */
    Set<Flight> getFlightsByDestinations (Long destinationId);
    
}

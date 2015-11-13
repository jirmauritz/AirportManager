package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.entity.Destination;
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
     */
    void create(Destination destination);
	
	  /**
     * Update destination in database
     *
     * @param destination destination entity to update
     */
    void update(Destination destination);

    /**
     * Delete destination from database
     *
     * @param destination destination entity to delete
     */
    void delete(Destination destination);	

    /**
     * Find by id
     *
	 * @param id
     * @return set of destinations
     */
    Destination findById(Long id);

	/**
     * Find by airport code.
     *
	 * @param code airport code
     * @return set of destinations
     */
    Destination findByAirportCode(String code);
	
    /**
     * Find all destinations in specified country
     *
	 * @param country
     * @return set of destination identifiers
     */
    Set<Destination> findByCountry(String country);

    /**
     * Find all
     *
     * @return set of destinations
     */
    Set<Destination> findAll();
    
}
package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.DestinationCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;

import java.util.Set;

/**
 * Facade for the methods over the Destination entity.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */
public interface DestinationFacade {
	 /**
     * Create new entity and persist it
     *
     * @param destination destination entity to create
	 * @return id
     */
    Long create(DestinationCreateDTO destination);

	 /**
     * Delete destination from database
     *
     * @param id id of the destination entity to delete
     */
    void delete(Long id);	
	
    /**
     * Find by id
     *
	 * @param id - id of destination
     * @return set of destinations
     */
    DestinationSimpleDTO findById(Long id);

	 /**
     * Find by airport code
     *
	 * @param code - airport code
     * @return set of destinations
     */
    DestinationSimpleDTO findByAirportCode(String code);
	
    /**
     * Find all destinations in specified country
     *
	 * @param country - country of destination
     * @return set of destination identifiers
     */
    Set<DestinationSimpleDTO> findByCountry(String country);

    /**
     * Find all
     *
     * @return set of destinations
     */
    Set<DestinationSimpleDTO> findAll();

    /**
     * Update destination.
     *
     * @param destination Destination to be updated.
     */
    void update(DestinationSimpleDTO destination);
}

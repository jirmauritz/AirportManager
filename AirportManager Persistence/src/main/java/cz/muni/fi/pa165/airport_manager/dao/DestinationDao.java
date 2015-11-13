package cz.muni.fi.pa165.airport_manager.dao;

import cz.muni.fi.pa165.airport_manager.entity.Destination;

import java.util.Set;

/**
 * Manages destinations in database
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public interface DestinationDao {

    /**
     * Create new entity and persist it
     *
     * @param destination destination entity to create
     */
    void create(Destination destination);

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
	 * @param name airport code
     * @return set of destinations
     */
    Destination findByName(String name);

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

}
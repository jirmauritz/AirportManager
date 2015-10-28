package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Destination;
import java.util.Set;

/**
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public interface DestinationDao {

    /**
     * Create new entity and persist it
     *
     * @param destination
     */
    void create(Destination destination);

    /**
     * Find by id
     *
     * @return set of destinations
     */
    Destination findById(Long id);

    /**
     * Find all destinations in specified country
     *
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
     * @param destination
     */
    void update(Destination destination);

    /**
     * Delete destination from database
     *
     * @param destination
     */
    void delete(Destination destination);

}
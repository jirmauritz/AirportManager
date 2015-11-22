package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import java.util.Date;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service for Airplane
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@Service
public interface AirplaneService {
    
    /**
     * Create new persisted entity.
     *
     * @param airplane entity
     * @throws IllegalStateException in case of no name, type or invalid capacity
     * or existing id
     */
    Airplane create(Airplane airplane);

    /**
     * Updates entity.
     *
     * @param airplane updated airplane
     * @throws IllegalStateException in case of no name, type or invalid capacity
     * or changed id
     */
    Airplane update(Airplane airplane);

    /**
     * Delete entity by id.
     *
     * @param id database id
     * @throws IllegalArgumentException in case of invalid id
     */
    void delete(Long id);

    /**
     * Finds by id. 
     *
     * @param id database id
     * @return Airplane or null
     */
    Airplane findById(Long id);

    /**
     * Find and return all airplanes.
     *
     * @return set of all Airplanes
     */
    Set<Airplane> findAll();

    /**
     * Find all by airplane type
     *
     * @return set of Airplanes
     * @throws IllegalArgumentException in case of invalid type
     */
    Set<Airplane> findByType(AirplaneType type) throws IllegalArgumentException;
    
    /**
     * Find all airplanes with minimal capacity.
     *
     * @return set of Airplanes
     * @throws IllegalArgumentException in case of illegal value of capacity
     */
    Set<Airplane> findByMinCapacity(int minCapacity)
            throws IllegalArgumentException;
    
    /**
     * Check if airplane is available in entered time range.
     *
     * @return boolean
     * @throws IllegalArgumentException in case of invalid id and time range
     */
    boolean isAvailable(Long id, final Date from, final Date to) 
            throws IllegalArgumentException;
    
    
    /**
     * Return all available airplanes in entered time range.
     *
     * @return set of Airplanes
     * @throws IllegalArgumentException in case of invalid time range
     */
    Set<Airplane> getAllAvailable(final Date from, final Date to)
            throws IllegalArgumentException;

}

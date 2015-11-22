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
     */
    void create(Airplane airplane);

    /**
     * Updates entity.
     *
     * @param airplane updated airplane
     */
    void update(Airplane airplane);

    /**
     * Delete entity by id.
     *
     * @param id database id
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
     */
    Set<Airplane> findByType(AirplaneType type);
    
    /**
     * Find all airplanes with minimal capacity.
     *
     * @return set of Airplanes
     */
    Set<Airplane> findByMinCapacity(int minCapacity);
    
    /**
     * Check if airplane is available in entered time range.
     *
     * @return boolean
     */
    boolean isAvailable(Long id, final Date from, final Date to);
    
    
    /**
     * Return all available airplanes in entered time range.
     *
     * @return set of Airplanes
     */
    Set<Airplane> getAllAvailable(final Date from, final Date to);

}

package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * Create new created id of persisted entity.
     *
     * @param airplane id of airplane entity
     * @return id of newly created airplane
     */
    Long create(Airplane airplane);

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
     * @param type airplane type
     * @return set of Airplanes
     */
    Set<Airplane> findByType(AirplaneType type);
    
    /**
     * Find all airplanes with minimal capacity or higher.
     *
     * @param minCapacity minimal legal value
     * @return set of Airplanes
     */
    Set<Airplane> findByMinCapacity(int minCapacity);
    
    /**
     * Check if airplane is available in entered time range.
     *
     * @param id airplanes id
     * @param from start of the time range
     * @param to end of the time range
     * @return true if available, false if not
     */
    boolean isAvailable(Long id, final Date from, final Date to);

    /**
     * Return all available airplanes in entered time range.
     *
     * @param from start of the time range
     * @param to end of the time range
     * @return set of Airplanes
     */
    Set<Airplane> getAllAvailable(final Date from, final Date to);

}

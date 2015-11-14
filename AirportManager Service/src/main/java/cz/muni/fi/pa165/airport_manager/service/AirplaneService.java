package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import org.springframework.stereotype.Service;

import java.util.SortedSet;

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
     * @param Airplane entity
     */
    void create(Airplane airplane);

    /**
     * Updates entity.
     *
     * @param Airplane updated airplane
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
     * @return sorted set of all Airplanes
     */
    SortedSet<Airplane> findAll();

    /**
     * Find all by airplane type
     *
     * @return sorted set of Airplanes
     */
    SortedSet<Airplane> findByType(AirplaneType type);
    
    /**
     * Find all airplanes with minimal capacity.
     *
     * @return sorted set of Airplanes
     */
    SortedSet<Airplane> findByMinCapacity(int minCapacity);

}

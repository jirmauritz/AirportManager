package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import java.util.SortedSet;

/**
 * Facade interface for Airplane entity
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public interface AirplaneFasade {

    /**
     * Create a new AirplaneDTO
     *
     * @param AirplaneCreateDTO entity to create
     */
    void createAirplane(AirplaneCreateDTO airplane);

    /**
     * Get by id
     *
     * @param id database id
     * @return sorted set of airplane DTOs
     */
    AirplaneDTO getAirplane(Long id);

    /**
     * Delete airplane by it's id
     * @param id
     */
    void deleteAirplaneById(Long id);

    /**
     * Find all Airplanes
     *
     * @return sorted set of airplane DTOs
     */
    SortedSet<AirplaneDTO> findAll();
    
    /**
     * Find all by airplane type
     *
     * @return sorted set of airplane DTOs
     */
    SortedSet<AirplaneDTO> findByType(AirplaneType type);
    
    /**
     * Find all airplanes with minimal capacity
     *
     * @return sorted set of airplane DTOs
     */
    SortedSet<AirplaneDTO> findByMinCapacity(int minCapacity);

}

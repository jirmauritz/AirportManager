package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import java.util.Set;

/**
 * Facade interface for Airplane entity
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public interface AirplaneFasade {

    /**
     * Create and persist a new airplane
     *
     * @param airplane entity to create
     * @return id of newly created airplane
     */
    Long createAirplane(AirplaneCreateDTO airplane);

    /**
     * Get by id
     *
     * @param id database id
     * @return Airplane with corresponding id
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
     * @return set of airplane DTOs
     */
    Set<AirplaneDTO> findAll();
    
    /**
     * Find all by airplane type
     *
     * @param type of airplane
     * @return set of airplane DTOs
     */
    Set<AirplaneDTO> findByType(AirplaneType type);
    
    /**
     * Find all airplanes with minimal capacity
     *
     * @param minCapacity minimal legal value
     * @return set of airplane DTOs
     */
    Set<AirplaneDTO> findByMinCapacity(int minCapacity);

}

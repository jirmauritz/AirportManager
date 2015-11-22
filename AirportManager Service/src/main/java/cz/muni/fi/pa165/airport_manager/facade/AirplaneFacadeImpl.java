package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import cz.muni.fi.pa165.airport_manager.service.AirplaneService;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Implementation of Airplane facade
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@Facade
public class AirplaneFacadeImpl implements AirplaneFasade {

    @Autowired
    private AirplaneService airplaneService;
    @Autowired
    private MappingService mappingService;

    @Override
    public AirplaneCreateDTO createAirplane(AirplaneCreateDTO airplane) {
        Objects.requireNonNull(airplane);

        return mappingService.mapTo(airplaneService.create(mappingService.mapTo(airplane, 
                Airplane.class)), AirplaneCreateDTO.class);
    }
    
    @Override
    public AirplaneDTO getAirplane(Long id) {
        Objects.requireNonNull(id);
        return mappingService.mapTo(airplaneService.findById(id), AirplaneDTO.class);
    }
    
    @Override
    public void deleteAirplaneById(Long id) {
        Objects.requireNonNull(id);
        airplaneService.delete(id);
    }

    @Override
    public Set<AirplaneDTO> findAll() {
        Set<Airplane> airplanes = airplaneService.findAll();
        return new HashSet<>(mappingService.mapTo(airplanes, AirplaneDTO.class));
    }
    
    @Override
    public Set<AirplaneDTO> findByType(AirplaneType type) {
        Objects.requireNonNull(type);

        Set<Airplane> airplanes = airplaneService.findByType(type);
        return new HashSet<>(mappingService.mapTo(airplanes, AirplaneDTO.class));
    }

    @Override
    public Set<AirplaneDTO> findByMinCapacity(int minCapacity) {
        Set<Airplane> airplanes = airplaneService.findByMinCapacity(minCapacity);
        return new HashSet<>(mappingService.mapTo(airplanes, AirplaneDTO.class));
    }
}

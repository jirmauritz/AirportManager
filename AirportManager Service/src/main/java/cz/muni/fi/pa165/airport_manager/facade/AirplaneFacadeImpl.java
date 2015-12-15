package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import cz.muni.fi.pa165.airport_manager.service.AirplaneService;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation of Airplane facade
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@Facade
@Service
public class AirplaneFacadeImpl implements AirplaneFacade {

    @Autowired
    private AirplaneService airplaneService;
    @Autowired
    private MappingService mappingService;

    @Override
    public Long createAirplane(AirplaneCreateDTO airplane) {
        Objects.requireNonNull(airplane);
        return airplaneService.create(mappingService.mapTo(airplane, Airplane.class));
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
        return new HashSet<>(mappingService.mapTo(airplaneService.findAll(), AirplaneDTO.class));
    }
    
    @Override
    public Set<AirplaneDTO> findByType(AirplaneType type) {
        Objects.requireNonNull(type);
        return new HashSet<>(mappingService.mapTo(airplaneService.findByType(type), AirplaneDTO.class));
    }

    @Override
    public Set<AirplaneDTO> findByMinCapacity(int minCapacity) {
        return new HashSet<>(mappingService.mapTo(airplaneService.findByMinCapacity(minCapacity), AirplaneDTO.class));
    }

    @Override
    public Set<AirplaneDTO> getAllAvailable(Date from, Date to) {
        return new HashSet<>(mappingService.mapTo(airplaneService.getAllAvailable(from, to), AirplaneDTO.class));
    }
}


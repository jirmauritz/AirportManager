package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightSimpleDTO;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.service.FlightService;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Facade implementation for FlightFacade
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public class FlightFacadeImpl implements FlightFacade{

    @Autowired
    private FlightService flightService;

    @Autowired
    private MappingService mappingService;


    @Override
    public void create(FlightCreateDTO flight) {
        flightService.create(mappingService.mapTo(flight, Flight.class));
    }

    @Override
    public FlightDTO getFlight(Long id) {
        return mappingService.mapTo(flightService.findById(id),FlightDTO.class);
    }

    @Override
    public void delete(Long id) {
        flightService.delete(id);
    }

    @Override
    public Set<FlightSimpleDTO> getFlights() {
        return mappingService.mapTo(flightService.findAll(),FlightSimpleDTO.class);

    }

    @Override
    public void addSteward(Long id) {
        //TODO
    }

    @Override
    public void removeSteward(Long id) {
        //TODO
    }

    @Override
    public void update(FlightSimpleDTO flight) {
        //TODO
    }
}

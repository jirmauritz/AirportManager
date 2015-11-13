package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightSimpleDto;
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
    public void create(FlightDTO flight) {
    if (flight.getId() == null){
        flightService.create(mappingService.mapTo(flight, Flight.class));
    } else {
        flightService.update(mappingService.mapTo(flight, Flight.class));
    }
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
    public Set<FlightSimpleDto> getFlights() {
        return mappingService.mapTo(flightService.findAll(),FlightSimpleDto.class);

    }
}

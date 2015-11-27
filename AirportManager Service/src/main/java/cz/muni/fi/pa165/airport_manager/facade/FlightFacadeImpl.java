package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightSimpleDTO;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.service.FlightService;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import cz.muni.fi.pa165.airport_manager.service.StewardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.Set;

/**
 * Facade implementation for FlightFacade.
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
@Facade
public class FlightFacadeImpl implements FlightFacade{

    @Autowired
    private FlightService flightService;

    @Autowired
    private MappingService mappingService;

    @Autowired
    private StewardService stewardService;

    @Override
    public Long create(FlightCreateDTO flight) {
        Objects.requireNonNull(flight);
        return flightService.create(mappingService.mapTo(flight, Flight.class));
    }

    @Override
    public FlightDTO getFlight(Long id) {
        Objects.requireNonNull(id);
        return mappingService.mapTo(flightService.findById(id),FlightDTO.class);
    }

    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id);
        flightService.delete(id);
    }

    @Override
    public Set<FlightSimpleDTO> getFlights() {
        return mappingService.mapTo(flightService.findAll(), FlightSimpleDTO.class);
    }

    @Override
    public void addSteward(Long stewardId, Long flightId) {
        Objects.requireNonNull(stewardId);
        Objects.requireNonNull(flightId);

        Flight flight = flightService.findById(flightId);
        Steward steward = stewardService.findSteward(stewardId);
        if (!flight.getStewards().contains(steward) &&
                stewardService.isAvailable(stewardId,flight.getDeparture(), flight.getArrival())) {
            flight.addSteward(steward);
        }

        flightService.update(flight);
    }

    @Override
    public void removeSteward(Long stewardId, Long flightId) {
        Objects.requireNonNull(stewardId);
        Objects.requireNonNull(flightId);

        Flight flight = flightService.findById(flightId);
        Steward steward = stewardService.findSteward(stewardId);
        if ( flight.getStewards().contains(steward)) {
            flight.removeSteward(steward);
        }
        flightService.update(flight);
    }

    @Override
    public void update(FlightSimpleDTO flight) {
        Objects.requireNonNull(flight);

        flightService.update(mappingService.mapTo(flight, Flight.class));
    }
}

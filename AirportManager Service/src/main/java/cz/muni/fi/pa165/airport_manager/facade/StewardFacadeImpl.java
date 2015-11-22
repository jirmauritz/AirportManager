package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardSimpleDTO;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import cz.muni.fi.pa165.airport_manager.service.StewardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Basic implementation of steward facade.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Facade
public class StewardFacadeImpl implements StewardFacade {

    private @Autowired StewardService stewardService;
    private @Autowired MappingService mappingService;

    @Override
    public Set<StewardSimpleDTO> getAllStewards() {
        Set<Steward> stewards = stewardService.findAllStewards();
        return new HashSet<>(mappingService.mapTo(stewards, StewardSimpleDTO.class));
    }

    @Override
    public StewardDTO createSteward(final StewardCreateDTO steward) {
        Objects.requireNonNull(steward);

        return mappingService.mapTo(
                stewardService.createSteward(
                        mappingService.mapTo(steward, Steward.class)
                ),
                StewardDTO.class
        );
    }

    @Override
    public StewardDTO getSteward(Long id) {
        Objects.requireNonNull(id);

        return mappingService.mapTo(
                stewardService.findSteward(id),
                StewardDTO.class
        );
    }

    @Override
    public void deleteSteward(Long id) {
        Objects.requireNonNull(id);

        stewardService.deleteSteward(id);
    }

    @Override
    public StewardDTO updateNames(
            Long id,
            String firstName,
            String lastName
    ) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);

        final StewardSimpleDTO steward = this.getSteward(id);

        steward.setFirstName(firstName);
        steward.setLastName(lastName);

        return mappingService.mapTo(
                stewardService.updateSteward(
                    mappingService.mapTo(steward, Steward.class)
                ),
                StewardDTO.class
        );
    }

    @Override
    public Set<FlightDTO> getAllFlightsForSteward(Long id) {
        return this.getSteward(id).getFlights();
    }
}

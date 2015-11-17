package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardRichDTO;
import cz.muni.fi.pa165.airport_manager.entity.Steward;
import cz.muni.fi.pa165.airport_manager.service.MappingService;
import cz.muni.fi.pa165.airport_manager.service.StewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
    public SortedSet<StewardDTO> getAllStewards() {
        Set<Steward> stewards = stewardService.findAllStewards();
        return new TreeSet<>(mappingService.mapTo(stewards, StewardDTO.class));
    }

    @Override
    public StewardRichDTO createSteward(final StewardCreateDTO steward) {
        Objects.requireNonNull(steward);

        return mappingService.mapTo(
                stewardService.createSteward(
                        mappingService.mapTo(steward, Steward.class)
                ),
                StewardRichDTO.class
        );
    }

    @Override
    public StewardRichDTO getSteward(final StewardDTO steward) {
        Objects.requireNonNull(steward);

        if (steward instanceof StewardRichDTO) {
            return (StewardRichDTO) steward;
        }

        return mappingService.mapTo(
                stewardService.findSteward(steward.getId()),
                StewardRichDTO.class
        );
    }

    @Override
    public boolean deleteSteward(final StewardDTO steward) {
        Objects.requireNonNull(steward);

        return stewardService.deleteSteward(
                mappingService.mapTo(steward, Steward.class)
        );
    }

    @Override
    public StewardRichDTO updateNames(
            final StewardDTO steward,
            String firstName,
            String lastName
    ) {
        Objects.requireNonNull(steward);
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);

        steward.setFirstName(firstName);
        steward.setLastName(lastName);

        return mappingService.mapTo(
                stewardService.updateSteward(
                    mappingService.mapTo(steward, Steward.class)
                ),
                StewardRichDTO.class
        );
    }

    @Override
    public SortedSet<FlightDTO> getAllFlightsForSteward(final StewardDTO steward) {
        return this.getSteward(steward).getFlights();
    }
}

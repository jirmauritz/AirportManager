package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardSimpleDTO;

import java.util.Set;

/**
 * Facade for the operations with Stewards.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public interface StewardFacade {

    /**
     * Returns all stewards.
     *
     * @return all stewards
     */
    Set<StewardSimpleDTO> getAllStewards();

    /**
     * Creates and saves new steward.
     *
     * @param steward steward to be created
     * @return id of the created steward
     */
    Long createSteward(StewardCreateDTO steward);

    /**
     * Returns the specified steward with more detail.
     *
     * @param id id of steward to get detail for
     * @return Steward with all details
     */
    StewardDTO getSteward(Long id);

    /**
     * Deletes specified steward.
     *
     * @param id id of steward to be deleted
     */
    void deleteSteward(Long id);

    /**
     * Updates the names of the specified steward.
     *
     * @param id id of steward to get updated names
     * @param firstName new first name of the steward
     * @param lastName new last name of the steward
     */
    void updateNames(Long id, String firstName, String lastName);

    /**
     * This is just convenience method for {@link StewardFacade#getSteward(Long)}.getFlights()
     *
     * @param id id of steward to find flights for
     * @return sorted set of flights
     */
    Set<FlightDTO> getAllFlightsForSteward(Long id);
}

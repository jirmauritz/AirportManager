package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightSimpleDTO;

import java.util.Set;

/**
 * Facade for the methods over the Flight entity.
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public interface FlightFacade {

    /**
     * Create new entity and persist it.
     *
     * @param flight entity to create
     * @return id
     */
    Long create(FlightCreateDTO flight);

    /**
     * Get flight by given id.
     *
     * @param id
     * @return set of destinations
     */
    FlightDTO getFlight(Long id);

    /**
     * Delete flight identified by id.
     *
     * @param id
     */
    void delete(Long id);

    /**
     * Find all
     *
     * @return set of destinations
     */
    Set<FlightSimpleDTO> getFlights();

    /**
     * Add steward to the flight, if given Steward is not already assigned for
     * given flight and if he/she is available.
     *
     * @param stewardId id of steward to be added
     * @param flightId id of flight
     */
    void addSteward(Long stewardId, Long flightId);

    /**
     * Remove steward from the flight, if given steward is assigned for given
     * flight.
     *
     * @param stewardId id of steward to be removed
     * @param flightId id of flight
     */
    void removeSteward(Long stewardId, Long flightId);

    /**
     * Update flight.
	 * The Stewards of the flight are unchanged when updating,
	 * use addSteward and removeSteward for this.
     *
     * @param flight Flight to be updated.
     */
    void update(FlightSimpleDTO flight);

}

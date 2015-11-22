package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.entity.Steward;

import java.util.Date;
import java.util.Set;

/**
 * Service for operations over database with stewards.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public interface StewardService {

    /**
     * Creates new steward in the database. Steward must have not assigned id
     * not have business id, must have empty Set of flights and must have
     * first and last name set.
     *
     * @param steward steward to be created
     * @return newly created steward with id and business id set
     */
    Steward createSteward(Steward steward);

    /**
     * Looks up the steward id the database by given id. If the steward
     * with specified id does not exist, null is returned.
     *
     * @param id id of the steward
     * @return steward with specified id or null
     */
    Steward findSteward(Long id);

    /**
     * Find and returns all stewards.
     *
     * @return set of stewards
     */
    Set<Steward> findAllStewards();

    /**
     * Updates the steward in the database. Only change of names and flights
     * is allowed, otherwise, throws IllegalStateException.
     *
     * @param steward steward with changed first or last name
     * @return new state of steward
     * @throws IllegalStateException if anything else, than names or flights, has changed
     */
    Steward updateSteward(Steward steward) throws IllegalStateException;

    /**
     * Deletes the steward from database.
     *
     * @param id id of steward to be deleted
     */
    void deleteSteward(Long id);

    /**
     * <p>Checks, if the steward (recognized by id) is available within the specified interval.
     * Steward is not available if any of the steward flights ends in the specified interval, starts
     * in the specified interval or starts before and ends after the specified interval.
     * More formally, steward is not available if:
     *
     * <p><code>
     *      (end  .after (flight.getDeparture()) && end  .before(flight.getArrival())) ||
     *      (start.after (flight.getDeparture()) && start.before(flight.getArrival())) ||
     *      (start.before(flight.getDeparture()) && end  .after (flight.getArrival()))
     * </code>
     *
     * @param id id of the steward steward to check availability for
     * @param start start of the interval
     * @param end endof the interval
     * @return true if available, false if not
     */
    boolean isAvailable(
            Long id,
            final Date start,
            final Date end
    );

}

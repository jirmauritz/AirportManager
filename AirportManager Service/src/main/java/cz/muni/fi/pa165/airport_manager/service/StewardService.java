package cz.muni.fi.pa165.airport_manager.service;

import cz.muni.fi.pa165.airport_manager.entity.Steward;

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
     * Updates the steward in the database. Only change of the first and last name
     * is allowed, otherwise, throws IllegalStateException.
     *
     * @param steward steward with changed first or last name
     * @return new state of steward
     */
    Steward updateSteward(Steward steward) throws IllegalStateException;

    /**
     * Deletes the steward from database.
     *
     * @param steward steward to be deleted
     * @return if the operation was successful
     */
    boolean deleteSteward(Steward steward);

}

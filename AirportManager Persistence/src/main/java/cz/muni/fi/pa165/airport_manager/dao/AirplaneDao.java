package cz.muni.fi.pa165.airport_manager.dao;

import cz.muni.fi.pa165.airport_manager.entity.Airplane;

import java.util.Set;

/**
 * Manages the Airplanes in database.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public interface AirplaneDao {

    /**
     * Persists specified airplane in the database.
     *
     * @param airplane entity to persist
     */
    void create(Airplane airplane);

    /**
     * Finds and returns entity from the database by the specified id. If there is no such entity, null is returned.
     *
     * @param id an id to look for
     * @return Airplane with specified id or null
     */
    Airplane findById(Long id);

    /**
     * Finds and returns all Airplanes in the database. If there are no airplanes, empty collection is returned.
     *
     * @return Set containing all Airplanes or empty Set
     */
    Set<Airplane> findAll();

    /**
     * Updates the state of the specified entity in the database. The entity must have attribute id set. Specified
     * entity should not be artificially created, but should be the one returned from database.
     *
     * @param airplane entity to update
     */
    void update(Airplane airplane);

    /**
     * Deletes the specified entity from the database. The entity must have attribute id set. Specified entity should
     * not be artificially created, but should be the one returned from database.
     *
     * @param airplane entity to delete
     */
    void delete(Airplane airplane);

    /**
     * Finds and returns all Airplanes with at least specified capacity.
     *
     * @param capacity capacity of airplanes to find
     * @return set of airplanes
     */
    Set<Airplane> findByMinCapacity(int capacity);

    /**
     * Finds and returns Airplane with specified name.
     *
     * @param name name of airplanes to find
     * @return an Airplane
     */
    Airplane findByName(String name);

    /**
     * Finds and returns all Airplanes with specified type.
     *
     * @param type type of airplanes to find
     * @return set of airplanes
     */
    Set<Airplane> findByType(String type);

}

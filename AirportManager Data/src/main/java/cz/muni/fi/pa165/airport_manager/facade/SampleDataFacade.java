package cz.muni.fi.pa165.airport_manager.facade;

/**
 * Sample data for AirportManager
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public interface SampleDataFacade {

    /**
     * Loads data into application database
     */
    void loadData();

    /**
     * Loads users for authentication
     */
    void loadUsers();

}

package cz.muni.fi.pa165.airport_manager.facade;

import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardRichDTO;

import java.util.SortedSet;

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
    SortedSet<StewardDTO> listAllStewards();

    /**
     * Creates and saves new steward.
     *
     * @param steward steward to be created
     * @return created steward
     */
    StewardRichDTO createSteward(StewardCreateDTO steward);

    /**
     * Returns the specified steward with more detail.
     *
     * @param steward Steward to get detail for
     * @return Steward with all details
     */
    StewardRichDTO getSteward(StewardDTO steward);

    /**
     * Deletes specified steward.
     *
     * @param steward Steward to be deleted
     * @return if the operation was successful
     */
    boolean deleteSteward(StewardDTO steward);

    /**
     * Updates the names of the specified steward.
     *
     * @param steward Steward to get updated names
     * @param firstName new first name of the steward
     * @param lastName new last name of the steward
     * @return newly updated steward
     */
    StewardRichDTO updateNames(StewardDTO steward, String firstName, String lastName);

//    /**
//     * This is just convenience method for {@link StewardFacade#updateNames(StewardDTO, String, String)}.
//     * Last name is without change.
//     *
//     * @param steward Steward to get updated names
//     * @param firstName new first name of the steward
//     * @return newly updated steward
//     */
//    default StewardRichDTO updateFirstName(StewardDTO steward, String firstName) {
//        return updateNames(steward, firstName, steward.getLastName());
//    }
//
//    /**
//     * This is just convenience method for {@link StewardFacade#updateNames(StewardDTO, String, String)}.
//     * First name is without change.
//     *
//     * @param steward Steward to get updated names
//     * @param lastName new last name of the steward
//     * @return newly updated steward
//     */
//    default StewardRichDTO updateLastName(StewardDTO steward, String lastName) {
//        return updateNames(steward, steward.getFirstName(), lastName);
//    }

}

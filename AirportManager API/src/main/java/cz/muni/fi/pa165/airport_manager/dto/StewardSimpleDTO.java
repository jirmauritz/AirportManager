package cz.muni.fi.pa165.airport_manager.dto;

import java.util.Objects;

/**
 * Class for showing Stewards. Main purpose of this class is for referencing
 * stewards in another class and for showing collections of them.
 *
 * Class is immutable.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public class StewardSimpleDTO extends StewardCreateDTO {

    private Long id;

    /**
     * Returns the database id of the steward
     *
     * @return database id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the database id of this steward to the specified one.
     *
     * @param id new database id
     */
    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "StewardSimpleDTO{" +
                "firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                '}';
    }
}

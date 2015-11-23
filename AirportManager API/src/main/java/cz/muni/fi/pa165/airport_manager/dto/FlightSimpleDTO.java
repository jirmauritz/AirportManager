package cz.muni.fi.pa165.airport_manager.dto;


/**
 * Data transfer object for the entity Flight.
 * DTO is used for listing flights.
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public class FlightSimpleDTO extends FlightCreateDTO {

    private Long id;

    /**
     * Gets ID of the Flight.
     *
     * @return the id of the Flight.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets ID to the Flight.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
}
package cz.muni.fi.pa165.airport_manager.dto;


import java.util.Set;

/**
 * Data transfer object for the entity Flight.
 * DTO is used for showing the detail of flight.
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public class FlightDTO extends FlightSimpleDTO {

    private Set<StewardSimpleDTO> stewards;

    /**
     * Gets Stewards of the Flight.
     *
     * @return set of stewards
     */
    public Set<StewardSimpleDTO> getStewards() {
        return stewards;
    }

    /**
     * Sets Stewards to the Flight.
     *
     * @param stewards
     */
    public void setStewards(Set<StewardSimpleDTO> stewards) {
        this.stewards = stewards;
    }

}


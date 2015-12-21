package cz.muni.fi.pa165.airport_manager.dto;

import java.util.*;

/**
 * Class for showing Stewards. Main purpose of this class is for showing
 * steward in detail.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public final class StewardDTO extends StewardSimpleDTO {

    private Set<FlightSimpleDTO> flights = new HashSet<>();

    /**
     * Returns a view of sorted set of the flights assigned to this steward
     *
     * @return set of flights
     */
    public Set<FlightSimpleDTO> getFlights() {
        return new HashSet<>(this.flights);
    }

    /**
     * Sets the flights of this steward to the specified ones. Given collection is copied.
     *
     * @param flights new flights
     */
    public void setFlights(final Set<FlightSimpleDTO> flights) {
        this.flights = new HashSet<>(flights);
    }

    @Override
    public String toString() {
        return "StewardDTO{" +
                "firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", flights=" + this.getFlights() +
                '}';
    }
}

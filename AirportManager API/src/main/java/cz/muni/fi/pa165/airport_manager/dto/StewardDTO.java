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

    private Set<FlightDTO> flights = new HashSet<>();

    /**
     * Returns a view of sorted set of the flights assigned to this steward
     *
     * @return set of flights
     */
    public Set<FlightDTO> getFlights() {
        return Collections.unmodifiableSet(flights);
    }

    /**
     * Sets the flights of this steward to the specified ones. Given collection is copied.
     *
     * @param flights new flights
     */
    public void setFlights(final Set<FlightDTO> flights) {
        this.flights = new HashSet<>(flights);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StewardDTO)) return false;
        if (!super.equals(o)) return false;

        final StewardDTO that = (StewardDTO) o;

        return this.getFlights().equals(that.getFlights());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.getFlights().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "StewardDTO{" +
                "firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", businessId=" + this.getBusinessId() +
                ", flights=" + this.getFlights() +
                '}';
    }
}

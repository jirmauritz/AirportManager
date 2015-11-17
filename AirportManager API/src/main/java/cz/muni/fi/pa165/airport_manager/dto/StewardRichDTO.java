package cz.muni.fi.pa165.airport_manager.dto;

import java.util.*;

/**
 * Class for showing Stewards. Main purpose of this class is for showing
 * steward in detail.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public final class StewardRichDTO extends StewardDTO {

    private SortedSet<FlightDTO> flights = new TreeSet<>();

    /**
     * Returns a view of sorted set of the flights assigned to this steward
     *
     * @return set of flights
     */
    public SortedSet<FlightDTO> getFlights() {
        return Collections.unmodifiableSortedSet(flights);
    }

    /**
     * Sets the flights of this steward to the specified ones. Given collection is copied.
     *
     * @param flights new flights
     */
    public void setFlights(final Set<FlightDTO> flights) {
        Objects.requireNonNull(flights);
        this.flights = new TreeSet<>(flights);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StewardRichDTO)) return false;
        if (!super.equals(o)) return false;

        final StewardRichDTO that = (StewardRichDTO) o;

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
        return "StewardRichDTO{" +
                "firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", businessId=" + this.getBusinessId() +
                ", flights=" + this.getFlights() +
                '}';
    }
}

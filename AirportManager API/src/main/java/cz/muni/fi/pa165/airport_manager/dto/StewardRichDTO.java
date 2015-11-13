package cz.muni.fi.pa165.airport_manager.dto;

import java.util.Collections;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class for showing Stewards. Main purpose of this class is for showing
 * steward in detail.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public final class StewardRichDTO extends StewardDTO {

    private SortedSet<FlightDTO> flights;

    private StewardRichDTO(
            String firstName,
            String lastName,
            Long businessId,
            final SortedSet<FlightDTO> flights
    ) {
        super(firstName, lastName, businessId);
        this.flights = flights;
    }

    /**
     * Creates new Steward. Given collection of stewards is copied
     *
     * @param firstName first name of the steward
     * @param lastName last name of the steward
     * @param businessId business Id of the steward
     * @param flights flights assigned to steward
     */
    public static StewardRichDTO create(
            String firstName,
            String lastName,
            Long businessId,
            final SortedSet<FlightDTO> flights
    ) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(businessId);
        Objects.requireNonNull(flights);
        return new StewardRichDTO(firstName, lastName, businessId, new TreeSet<>(flights));
    }

    /**
     * Returns sorted set of the flights assigned to steward
     *
     * @return set of flights
     */
    public SortedSet<FlightDTO> getFlights() {
        return Collections.unmodifiableSortedSet(flights);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StewardRichDTO)) return false;
        if (!super.equals(o)) return false;

        StewardRichDTO that = (StewardRichDTO) o;

        return getFlights().equals(that.getFlights());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getFlights().hashCode();
        return result;
    }
}

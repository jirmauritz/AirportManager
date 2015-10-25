package cz.muni.fi.pa165.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Entity Steward for storing into database. Steward is distinguishable by its first and last name.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */

@Getter
@Setter
@EqualsAndHashCode(of = {"firstName", "lastName"})
@ToString

@Entity
public class Steward {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @ManyToMany
    private Set<Flight> flights = new HashSet<>();

    /**
     * Constructs new entity Steward. Setting id at construction time is prohibited. Id should be set only by the
     * database. All flights are copied into a new collection, same flights are used.
     *
     * @param firstName first name of this steward
     * @param lastName last name of this steward
     * @param flights set of flights steward is assigned to
     */
    public Steward(
            final String firstName,
            final String lastName,
            final Set<Flight> flights
    ) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flights = new HashSet<>(flights);
    }

    /**
     * Adds flight to this steward.
     *
     * @param flight flight to assign
     */
    public final void addFlight(final Flight flight) {
        Objects.requireNonNull(flight);
        this.flights.add(flight);
    }

    /**
     * Removes assighed flight from this steward.
     *
     * @param flight flight to remove
     * @throws NoSuchElementException if the flight is not present
     */
    public final void removeFlight(final Flight flight) throws NoSuchElementException {
        Objects.requireNonNull(flight);
        if (!this.flights.contains(flight)) {
            throw new NoSuchElementException("No such element: " + flight);
        }
        this.flights.remove(flight);
    }

    // lombok override for unmodifiability
    public final Set<Flight> getFlights() {
        return Collections.unmodifiableSet(flights);
    }

}

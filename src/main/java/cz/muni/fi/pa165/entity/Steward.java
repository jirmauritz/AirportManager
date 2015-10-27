package cz.muni.fi.pa165.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Entity Steward for storing into database. Steward is distinguishable by its first and last name.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */

@Entity
public class Steward {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private Long businessId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @ManyToMany
    private Set<Flight> flights = new HashSet<>();

    public Steward() {}

    /**
     * Constructs new entity Steward. Setting id at construction time is prohibited. Id should be set only by the
     * database. All flights are copied into a new collection, same flights are used.
     *
     * @param businessId business id of the Steward, assigned by business managers
     * @param firstName first name of this steward
     * @param lastName last name of this steward
     * @param flights set of flights steward is assigned to
     */
    public Steward(
            final Long businessId,
            final String firstName,
            final String lastName,
            final Set<Flight> flights
    ) {
        this.businessId = businessId;
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

    public Long getBusinessId() {
        return businessId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public final Set<Flight> getFlights() {
        return Collections.unmodifiableSet(flights);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Steward)) return false;

        Steward steward = (Steward) o;

        if (!getBusinessId().equals(steward.getBusinessId())) return false;
        if (!getFirstName().equals(steward.getFirstName())) return false;
        return getLastName().equals(steward.getLastName());

    }

    @Override
    public int hashCode() {
        int result = getBusinessId().hashCode();
        result = 31 * result + getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Steward{" +
                "id=" + id +
                ", businessId=" + businessId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", flights=" + flights +
                '}';
    }

}

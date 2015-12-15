package cz.muni.fi.pa165.airport_manager.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
     * @param firstName first name of this steward
     * @param lastName last name of this steward
     * @param flights set of flights steward is assigned to
     */
    public Steward(
            final String firstName,
            final String lastName,
            final Set<Flight> flights
    ) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(flights);
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
     * Removes assigned flight from this steward.
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

	/**
	 * Get id of the Steward.
	 * @return id
	 */
    public Long getId() {
        return id;
    }

	/**
	 * Get first name of the Steward.
	 * @return first name
	 */
    public String getFirstName() {
        return firstName;
    }

	/**
	 * Get last name of the Steward.
	 * @return last name
	 */
    public String getLastName() {
        return lastName;
    }

	/**
	 * Get flights of the Steward.
	 * @return set of flights
	 */
    public final Set<Flight> getFlights() {
        return this.flights;
    }

	/**
	 * Set id of the Steward.
	 * @param id database id
	 */
    public void setId(Long id) {
        this.id = id;
    }

	/**
	 * Set first name of the Steward.
	 * @param firstName first name of steward
	 */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

	/**
	 * Set the last name of the Steward.
	 * @param lastName first name of steward
	 */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	/**
	 * Set the flight of the Steward.
	 * @param flights all flights of steward
	 */
    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Steward)) return false;

        Steward steward = (Steward) o;

        if (!getFirstName().equals(steward.getFirstName())) return false;
        return getLastName().equals(steward.getLastName());

    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Steward{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", flights=" + flights +
                '}';
    }

}

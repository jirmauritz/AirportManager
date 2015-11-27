package cz.muni.fi.pa165.airport_manager.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;


/**
 * Class defines one flight.
 * 
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */

@Entity
public class Flight {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Boolean international;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date departure;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date arrival;
	

	@ManyToMany
	private Set<Steward> stewards = new HashSet<>();
	
	@NotNull
	@ManyToOne
	private Airplane airplane;
	
	@NotNull
	@ManyToOne
	private Destination from;
	
	@NotNull
	@ManyToOne
	private Destination to;
	
	/**
	 * Parametric constructor to create filled entity Flight.
	 * 
	 * @param international - true if the flight is international
	 * @param departure - time of departure
	 * @param arrival - time of arrival
	 * @param stewards - set of stewards serving the flight
	 * @param airplane - airplane of the fligh
	 * @param from - departure destination
	 * @param to - arrival destination
	 */
	public Flight(Boolean international, Date departure, Date arrival,
			Set<Steward> stewards, Airplane airplane, Destination from,
			Destination to) {
		super();
		this.international = international;
		this.departure = new Date(departure.getTime());
		this.arrival = new Date(arrival.getTime());
		this.stewards.addAll(stewards);
		this.airplane = airplane;
		this.from = from;
		this.to = to;
	}
	
	/**
	 * Non-parametric constructor to create empty entity Flight.
	 */
	public Flight() {
		super();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((airplane == null) ? 0 : airplane.hashCode());
		result = prime * result + ((arrival == null) ? 0 : arrival.hashCode());
		result = prime * result
				+ ((departure == null) ? 0 : departure.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result
				+ ((international == null) ? 0 : international.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Flight))
			return false;
		Flight other = (Flight) obj;
		if (airplane == null) {
			if (other.getAirplane() != null)
				return false;
		} else if (!airplane.equals(other.getAirplane()))
			return false;
		if (arrival == null) {
			if (other.getArrival() != null)
				return false;
		} else if (! (arrival.getTime() == other.getArrival().getTime())) //override for Timestamp's violation of equals
			return false;
		if (departure == null) {
			if (other.getDeparture() != null)
				return false;
		} else if (! (departure.getTime() == other.getDeparture().getTime())) //override for Timestamp's violation of equals
			return false;
		if (from == null) {
			if (other.getFrom() != null)
				return false;
		} else if (!from.equals(other.getFrom()))
			return false;
		if (international == null) {
			if (other.isInternational() != null)
				return false;
		} else if (!international.equals(other.isInternational()))
			return false;
		if (to == null) {
			if (other.getTo() != null)
				return false;
		} else if (!to.equals(other.getTo()))
			return false;
		return true;
	}

	/**
	 * Get id of the flight.
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Is the flight international.
	 * @return true if the flight is international
	 */
	public Boolean isInternational() {
		return international;
	}

	/**
	 * Set international flag of the flight.
	 * @param international true if the flight is international
	 */
	public void setInternational(Boolean international) {
		this.international = international;
	}

	/**
	 * Get departure time of the flight.
	 * @return time
	 */
	public Date getDeparture() {
		return new Date(departure.getTime());
	}

	/**
	 * Set departure time of the flight.
	 * @param departure time
	 */
	public void setDeparture(Date departure) {
		this.departure = new Date(departure.getTime());
	}

	/**
	 * Get arrival time of the flight.
	 * @return time
	 */
	public Date getArrival() {
		return new Date(arrival.getTime());
	}

	/**
	 * Set arrival time of the flight.
	 * @param arrival time
	 */
	public void setArrival(Date arrival) {
		this.arrival = new Date(arrival.getTime());
	}

	/**
	 * Get set of Stewards of the flight.
	 * @return set of stewards
	 */
	public Set<Steward> getStewards() {
		return stewards;
	}

	/**
	 * Set set of stewards of the flight.
	 * @param stewards set of stewards
	 */
	public void setStewards(Set<Steward> stewards) {
		this.stewards = stewards;
	}

	/**
	 * Get airplane of the flight.
	 * @return airplane
	 */
	public Airplane getAirplane() {
		return airplane;
	}

	/**
	 * Set airplane of the flight.
	 * @param airplane airplane of flight
	 */
	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	/**
	 * Get departure destination of the flight.
	 * @return destination
	 */
	public Destination getFrom() {
		return from;
	}

	/**
	 * Set departure destination of the flight.
	 * @param from destination
	 */
	public void setFrom(Destination from) {
		this.from = from;
	}

	/**
	 * Get arrival destination of the flight.
	 * @return destination
	 */
	public Destination getTo() {
		return to;
	}

	/**
	 * Set arrival destination of the flight.
	 * @param to destination
	 */
	public void setTo(Destination to) {
		this.to = to;
	}

	/**
	 * Removes given steward from this flight. 
	 * 
	 * @param steward steward to be added
	 */
	public void removeSteward (Steward steward){
		Objects.requireNonNull(steward);
		if (!this.stewards.contains(steward)) {
            throw new NoSuchElementException("No such element: " + steward);
        }
		stewards.remove(steward);
	}

	/**
	 * Add given steward to this flight.
	 * 
	 * @param steward steward to be removed
	 */
	public void addSteward (Steward steward){
		Objects.requireNonNull(steward);
		stewards.add(steward);
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", from=" + from + ", to=" + to + "]";
	}
	
	
	
}

package cz.muni.fi.pa165.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


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
	
	@NotNull
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
	
	public Flight(Long id, Boolean international, Date departure, Date arrival,
			Set<Steward> stewards, Airplane airplane, Destination from,
			Destination to) {
		super();
		this.id = id;
		this.international = international;
		this.departure = new Date(departure.getTime());
		this.arrival = new Date(arrival.getTime());
		this.stewards.addAll(stewards);
		this.airplane = airplane;
		this.from = from;
		this.to = to;
	}
	
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
			if (other.getInternational() != null)
				return false;
		} else if (!international.equals(other.getInternational()))
			return false;
		if (to == null) {
			if (other.getTo() != null)
				return false;
		} else if (!to.equals(other.getTo()))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getInternational() {
		return international;
	}

	public void setInternational(Boolean international) {
		this.international = international;
	}

	public Date getDeparture() {
		return new Date(departure.getTime());
	}

	public void setDeparture(Date departure) {
		this.departure = new Date(departure.getTime());
	}

	public Date getArrival() {
		return new Date(arrival.getTime());
	}

	public void setArrival(Date arrival) {
		this.arrival = new Date(arrival.getTime());
	}

	public Set<Steward> getStewards() {
		return stewards;
	}

	public void setStewards(Set<Steward> stewards) {
		this.stewards = stewards;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public Destination getFrom() {
		return from;
	}

	public void setFrom(Destination from) {
		this.from = from;
	}

	public Destination getTo() {
		return to;
	}

	public void setTo(Destination to) {
		this.to = to;
	}

	/**
	 * Removes given steward from this flight. 
	 * 
	 * @param steward
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
	 * @param steward
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

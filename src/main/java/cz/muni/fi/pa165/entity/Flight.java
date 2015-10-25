package cz.muni.fi.pa165.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * Class defines one flight.
 * 
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"departure", "arrival", "from", "to"})

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
	private List<Steward> stewards = new ArrayList<Steward>();
	
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

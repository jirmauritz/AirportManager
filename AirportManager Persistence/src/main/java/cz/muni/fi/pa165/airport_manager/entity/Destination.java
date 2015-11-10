package cz.muni.fi.pa165.airport_manager.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Class represents location of an airport, characterized by its code, city and country.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */
@Entity
public class Destination {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(nullable=false,unique=true)
	private String name; // airport code
	
	@NotNull
	private String city;
	
	@NotNull
	private String country;

	/**
	 * Parametric constructor to create filled entity Airplane.
	 * 
	 * @param name - code of the airport
	 * @param city - city where the airport is located
	 * @param country - country of the airport
	 */
	public Destination(String name, String city, String country) {
		this.name = name;
		this.city = city;
		this.country = country;
	}

	/**
	 * Non-parametric constructor to create an empty entity Destination.
	 */
	public Destination() {
	}

	/**
	 * Get id of the destination.
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set id to the destination.
	 * @param id database id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get code of the airport
	 * @return code
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name to the destination.
	 * @param name code of the airport
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get city of the airport.
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set city to the destination.
	 * @param city where the airport is
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Get country where the airport is located.
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Set country to the destination.
	 * @param country of the destination
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Destination{" + "id=" + id + ", name=" + name + ", city=" + city + ", country=" + country + '}';
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 67 * hash + Objects.hashCode(this.name);
		hash = 67 * hash + Objects.hashCode(this.city);
		hash = 67 * hash + Objects.hashCode(this.country);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Destination)) {
			return false;
		}
		final Destination other = (Destination) obj;
		if (!Objects.equals(this.name, other.getName())) {
			return false;
		}
		if (!Objects.equals(this.city, other.getCity())) {
			return false;
		}
		if (!Objects.equals(this.country, other.getCountry())) {
			return false;
		}
		return true;
	}

}
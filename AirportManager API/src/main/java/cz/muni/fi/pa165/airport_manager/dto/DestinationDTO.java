package cz.muni.fi.pa165.airport_manager.dto;

import java.util.Objects;

/**
 * Data transfer object for the entity Destination.
 * Useful for getDestination methods.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */
public class DestinationDTO {

	private Long id;
	
	private String name; // airport code
	
	private String city;
	
	private String country;

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
	public int hashCode() {
		int hash = 7;
		hash = 41 * hash + Objects.hashCode(this.name);
		hash = 41 * hash + Objects.hashCode(this.city);
		hash = 41 * hash + Objects.hashCode(this.country);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DestinationDTO)) {
			return false;
		}
		final DestinationDTO other = (DestinationDTO) obj;
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

	@Override
	public String toString() {
		return "DestinationDTO{" + "id=" + id + ", name=" + name + ", city=" + city + ", country=" + country + '}';
	}
	
}

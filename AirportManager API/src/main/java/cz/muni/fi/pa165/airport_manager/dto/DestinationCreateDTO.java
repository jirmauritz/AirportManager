package cz.muni.fi.pa165.airport_manager.dto;

import java.util.Objects;

/**
 * Data transfer object for the creation of the entity Destination.
 * Useful for createDestination method.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */
public class DestinationCreateDTO {
	
	protected String name; // airport code
	
	protected String city;
	
	protected String country;

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
		int hash = 3;
		hash = 47 * hash + Objects.hashCode(this.name);
		hash = 47 * hash + Objects.hashCode(this.city);
		hash = 47 * hash + Objects.hashCode(this.country);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DestinationCreateDTO)) {
			return false;
		}
		final DestinationCreateDTO other = (DestinationCreateDTO) obj;
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

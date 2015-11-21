package cz.muni.fi.pa165.airport_manager.dto;

/**
 * Data transfer object for the entity Destination.
 * Useful for getDestination methods.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */
public class DestinationSimpleDTO extends DestinationCreateDTO {

	private Long id;

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

	@Override
	public String toString() {
		return "DestinationDTO{" + "id=" + id + ", name=" + name + ", city=" + city + ", country=" + country + '}';
	}
	
}

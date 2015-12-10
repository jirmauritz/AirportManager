package cz.muni.fi.pa165.airport_manager.dto;

/**
 * Data transfer object for the entity Airplane.
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public class AirplaneDTO extends AirplaneCreateDTO {

    private Long id;
    
    /**
     * Get id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id
     *
     * @param id database id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AirplaneDTO [id=" + id + ", name=" + super.getName() + ", type=" + 
                super.getType().toString() + ",capacity=" + super.getCapacity() + "]";
    }
}
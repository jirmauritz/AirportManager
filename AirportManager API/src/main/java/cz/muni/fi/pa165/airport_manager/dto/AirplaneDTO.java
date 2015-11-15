package cz.muni.fi.pa165.airport_manager.dto;

import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import java.util.Objects;

/**
 * Data transfer object for the entity Airplane.
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public class AirplaneDTO {

    private Long id;

    private String name;

    private AirplaneType type;

    private int capacity;
    
    /**
     * Non parametric constructor to create empty AirplaneDTO.
     */
    public AirplaneDTO() {
    }
    
    /**
     * Parametric constructor to create filled AirplaneDTO.
     *
     * @param id - database id
     * @param name - name of the plane
     * @param type - type of the plane
     * @param capacity - capacity of the plane
     */
    public AirplaneDTO(Long id, String name, AirplaneType type, int capacity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
    }

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

    /**
     * Get name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     *
     * @param name name of airplane
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get type
     *
     * @return type
     */
    public AirplaneType getType() {
        return type;
    }

    /**
     * Set type
     *
     * @param type type of plane
     */
    public void setType(AirplaneType type) {
        this.type = type;
    }

    /**
     * Set capacity
     *
     * @param capacity capacity of plane
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Get capacity
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        final int prime = 127;

        hash = prime * hash + Objects.hashCode(this.name);
        hash = prime * hash + Objects.hashCode(this.type);
        hash = prime * hash + Objects.hashCode(this.capacity);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AirplaneDTO)) {
            return false;
        }
        final AirplaneDTO other = (AirplaneDTO) obj;
        if (!Objects.equals(this.name, other.getName())) {
            return false;
        }
        if (!Objects.equals(this.type, other.getType())) {
            return false;
        }
        if (!Objects.equals(this.capacity, other.getCapacity())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AirplaneDTO [id=" + id + ", name=" + name + ", type=" + 
                type.toString() + ",capacity=" + capacity + "]";
    }

}
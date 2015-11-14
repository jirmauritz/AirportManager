package cz.muni.fi.pa165.airport_manager.dto;

import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import java.util.Objects;

/**
 * Data transfer object providing the creation of the Airplane.
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
public class AirplaneCreateDTO {
  
    private Long id;

    private String name;

    private AirplaneType type;

    private int capacity;
    
    /**
     * Non parametric constructor to create empty AirplaneCreateDTO.
     */
    public AirplaneCreateDTO() {
    }
    
    /**
     * Parametric constructor to create filled AirplaneCreateDTO.
     *
     * @param id - database id
     * @param name - name of the plane
     * @param type - type of the plane
     * @param capacity - capacity of the plane
     */
    public AirplaneCreateDTO(Long id, String name, AirplaneType type, int capacity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacity = capacity;
    }
    
    /**
     * Create new AirplaneCreateDTO object
     *
     * @param id - database id
     * @param name - name of the plane
     * @param type - type of the plane
     * @param capacity - capacity of the plane
     */
    public AirplaneCreateDTO create(Long id, String name, AirplaneType type, int capacity) {
        return new AirplaneCreateDTO(id, name, type, capacity);
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
     * Get name
     *
     * @return name
     */
    public String getName() {
        return name;
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

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
  
    private String name;

    private AirplaneType type;

    private int capacity;
    
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

    /**
     * Set name
     *
     * @param name name of Airplane
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set type
     *
     * @param type type of airplane
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
        if (!(obj instanceof AirplaneCreateDTO)) {
            return false;
        }
        final AirplaneCreateDTO other = (AirplaneCreateDTO) obj;
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

}
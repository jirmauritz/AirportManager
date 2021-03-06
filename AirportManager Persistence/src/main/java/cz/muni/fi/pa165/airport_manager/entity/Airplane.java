package cz.muni.fi.pa165.airport_manager.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Class defines general airplane
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull
    private String type;

    @NotNull
    private int capacity;

	/**
	 * Non parametric constructor to created empty entity Airplane.
	 */
    public Airplane() {
    }
    
	/**
	 * Parametric constructor to create filled entity Airplane.
	 * 
	 * @param name - name of the plane
	 * @param type - type of the plane
	 * @param capacity - capacity of the plane
	 */
    public Airplane(String name, String type, int capacity) {
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
    public String getType() {
        return type;
    }
	
	/**
     * Set type
     *
     * @param type type of plane
     */
    public void setType(String type) {
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
        if (!(obj instanceof Airplane)) {
            return false;
        }
        final Airplane other = (Airplane) obj;
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
        return "Airplane [id=" + id + ", name=" + name + ", type=" + type
                + ",capacity=" + capacity + "]";
    }

}

package cz.muni.fi.pa165.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

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

    private String type; //TODO, airplane classification

    private int capacity;

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

}

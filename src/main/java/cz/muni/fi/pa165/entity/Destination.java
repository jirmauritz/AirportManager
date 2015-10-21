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
	
	private String city;
	
	private String country;

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
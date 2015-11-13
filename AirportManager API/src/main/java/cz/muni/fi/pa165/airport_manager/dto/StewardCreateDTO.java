package cz.muni.fi.pa165.airport_manager.dto;

import java.util.Objects;

/**
 * Class for creating Stewards. Class is immutable.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public class StewardCreateDTO {

    private String firstName;
    private String lastName;

    protected StewardCreateDTO(
            String firstName,
            String lastName
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Creates new Steward
     *
     * @param firstName first name of the steward
     * @param lastName last name of the steward
     */
    public static StewardCreateDTO create(
            String firstName,
            String lastName
    ) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        return new StewardCreateDTO(firstName, lastName);
    }

    /**
     * Returns the first name of the steward
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the last name of the steward
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StewardCreateDTO)) return false;

        StewardCreateDTO that = (StewardCreateDTO) o;

        if (!getFirstName().equals(that.getFirstName())) return false;
        return getLastName().equals(that.getLastName());

    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        return result;
    }
}

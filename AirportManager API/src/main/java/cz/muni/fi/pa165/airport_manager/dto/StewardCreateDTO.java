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

    /**
     * Sets the first name of this steward to the specified one.
     *
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        Objects.requireNonNull(firstName);
        this.firstName = firstName;
    }

    /**
     * Sets the last name of this steward to the specified one.
     *
     * @param lastName new last name
     */
    public void setLastName(String lastName) {
        Objects.requireNonNull(lastName);
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StewardCreateDTO)) return false;

        final StewardCreateDTO that = (StewardCreateDTO) o;

        if (!this.getFirstName().equals(that.getFirstName())) return false;
        return this.getLastName().equals(that.getLastName());

    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + this.getLastName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "StewardCreateDTO{" +
                "firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                '}';
    }

}

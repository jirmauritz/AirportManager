package cz.muni.fi.pa165.airport_manager.dto;

import java.util.Objects;

/**
 * Class for showing Stewards. Main purpose of this class is for referencing
 * stewards in another class and for showing collections of them.
 *
 * Class is immutable.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
public class StewardDTO extends StewardCreateDTO implements Comparable<StewardDTO> {

    private Long id;
    private Long businessId;

    /**
     * Returns the business id of the steward
     *
     * @return business id
     */
    public Long getBusinessId() {
        return this.businessId;
    }

    /**
     * Returns the database id of the steward
     *
     * @return database id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the business id of this steward to the specified one.
     *
     * @param businessId new business id
     */
    public void setBusinessId(Long businessId) {
        Objects.requireNonNull(businessId);
        this.businessId = businessId;
    }
    /**
     * Sets the database id of this steward to the specified one.
     *
     * @param id new database id
     */
    public void setId(Long id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StewardDTO)) return false;
        if (!super.equals(o)) return false;

        final StewardDTO that = (StewardDTO) o;

        return this.getBusinessId().equals(that.getBusinessId());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.getBusinessId().hashCode();
        return result;
    }

    @Override
    public int compareTo(StewardDTO o) {
        Objects.requireNonNull(o);
        return this.businessId.compareTo(o.getBusinessId());
    }

    @Override
    public String toString() {
        return "StewardDTO{" +
                "firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", businessId=" + this.getBusinessId() +
                '}';
    }
}

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

    private Long businessId;

    protected StewardDTO(
            String firstName,
            String lastName,
            Long businessId
    ) {
        super(firstName, lastName);
        this.businessId = businessId;
    }

    /**
     * Creates new Steward
     *
     * @param firstName first name of the steward
     * @param lastName last name of the steward
     * @param businessId business Id of the steward
     */
    public static StewardDTO create(
            String firstName,
            String lastName,
            Long businessId
    ) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(businessId);
        return new StewardDTO(firstName, lastName, businessId);
    }

    /**
     * Returns the business id of the steward
     *
     * @return business id
     */
    public Long getBusinessId() {
        return businessId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StewardDTO)) return false;
        if (!super.equals(o)) return false;

        StewardDTO that = (StewardDTO) o;

        return getBusinessId().equals(that.getBusinessId());

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getBusinessId().hashCode();
        return result;
    }

    @Override
    public int compareTo(StewardDTO o) {
        Objects.requireNonNull(o);
        return businessId.compareTo(o.getBusinessId());
    }
}

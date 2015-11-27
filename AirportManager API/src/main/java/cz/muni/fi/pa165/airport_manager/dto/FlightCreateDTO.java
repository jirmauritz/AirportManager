package cz.muni.fi.pa165.airport_manager.dto;

import java.util.Date;

/**
 * Data transfer object for the entity Flight.
 * DTO is used for creation of the flight.
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public class FlightCreateDTO {

    private Boolean international;

    private Date departure;

    private Date arrival;

    private AirplaneDTO airplane;

    private DestinationSimpleDTO from;

    private DestinationSimpleDTO to;

    /**
     * Return boolean whether Flight is international.
     *
     * @return true, if Flight is international, false otherwise.
     */
    public Boolean getInternational() {
        return international;
    }

    /**
     * Sets whether Flight is international.
     *
     * @param international
     */
    public void setInternational(Boolean international) {
        this.international = international;
    }

    /**
     * Get departure date of the Flight.
     *
     * @return the departure date
     */
    public Date getDeparture() {
        return departure;
    }

    /**
     * Sets departure date of the Flight.
     *
     * @param departure
     */
    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    /**
     * Get arrival date of the Flight.
     *
     * @return the arrival date
     */
    public Date getArrival() {
        return arrival;
    }

    /**
     * Sets arrival date of the Flight.
     *
     * @param arrival
     */
    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    /**
     * Gets Airplane.
     *
     * @return the AirplaneDTO instance
     */
    public AirplaneDTO getAirplane() {
        return airplane;
    }

    /**
     * Sets Airplane.
     *
     * @param airplane
     */
    public void setAirplane(AirplaneDTO airplane) {
        this.airplane = airplane;
    }

    /**
     * Gets from-destination .
     *
     * @return the DestinationSimpleDTO from.
     */
    public DestinationSimpleDTO getFrom() {
        return from;
    }

    /**
     * Sets from-destination.
     *
     * @param from
     */
    public void setFrom(DestinationSimpleDTO from) {
        this.from = from;
    }

    /**
     * Gets to-destination.
     *
     * @return the DestinationSimpleDTO to
     */
    public DestinationSimpleDTO getTo() {
        return to;
    }

    /**
     * Sets to-destination.
     *
     * @param to
     */
    public void setTo(DestinationSimpleDTO to) {
        this.to = to;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((airplane == null) ? 0 : airplane.hashCode());
        result = prime * result + ((arrival == null) ? 0 : arrival.hashCode());
        result = prime * result
                + ((departure == null) ? 0 : departure.hashCode());
        result = prime * result + ((from == null) ? 0 : from.hashCode());
        result = prime * result
                + ((international == null) ? 0 : international.hashCode());
        result = prime * result + ((to == null) ? 0 : to.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FlightCreateDTO))
            return false;
        FlightCreateDTO other = (FlightCreateDTO) obj;
        if (airplane == null) {
            if (other.getAirplane() != null)
                return false;
        } else if (!airplane.equals(other.getAirplane()))
            return false;
        if (arrival == null) {
            if (other.getArrival() != null)
                return false;
        } else if (! (arrival.getTime() == other.getArrival().getTime())) //override for Timestamp's violation of equals
            return false;
        if (departure == null) {
            if (other.getDeparture() != null)
                return false;
        } else if (! (departure.getTime() == other.getDeparture().getTime())) //override for Timestamp's violation of equals
            return false;
        if (from == null) {
            if (other.getFrom() != null)
                return false;
        } else if (!from.equals(other.getFrom()))
            return false;
        if (international == null) {
            if (other.getInternational() != null)
                return false;
        } else if (!international.equals(other.getInternational()))
            return false;
        if (to == null) {
            if (other.getTo() != null)
                return false;
        } else if (!to.equals(other.getTo()))
            return false;
        return true;
    }
}


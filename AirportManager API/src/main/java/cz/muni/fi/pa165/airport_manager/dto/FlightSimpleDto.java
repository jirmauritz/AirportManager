package cz.muni.fi.pa165.airport_manager.dto;


import java.util.Date;

/**
 * Data transfer object for the entity Flight.
 * DTO is used for listing flights.
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public class FlightSimpleDto {
    private Long id;

    private Boolean international;

    private Date departure;

    private Date arrival;

    private AirplaneDTO airplane;

    private DestinationDTO from;

    private DestinationDTO to;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getInternational() {
        return international;
    }

    public void setInternational(Boolean international) {
        this.international = international;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public AirplaneDTO getAirplane() {
        return airplane;
    }

    public void setAirplane(AirplaneDTO airplane) {
        this.airplane = airplane;
    }

    public DestinationDTO getFrom() {
        return from;
    }

    public void setFrom(DestinationDTO from) {
        this.from = from;
    }

    public DestinationDTO getTo() {
        return to;
    }

    public void setTo(DestinationDTO to) {
        this.to = to;
    }
}

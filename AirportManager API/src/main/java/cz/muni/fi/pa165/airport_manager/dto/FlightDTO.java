package cz.muni.fi.pa165.airport_manager.dto;


import java.util.Date;
import java.util.Set;

/**
 * Data transfer object for the entity Flight.
 * DTO is used for showing the detail of flight.
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public class FlightDTO {

    private Long id;

    private Boolean international;

    private Date departure;

    private Date arrival;

    private AirplaneDTO airplane;

    private DestinationSimpleDTO from;

    private DestinationSimpleDTO to;

    private Set<StewardDTO> stewards;

    public Set<StewardDTO> getStewards() {
        return stewards;
    }

    public void setStewards(Set<StewardDTO> stewards) {
        this.stewards = stewards;
    }

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

    public DestinationSimpleDTO getFrom() {
        return from;
    }

    public void setFrom(DestinationSimpleDTO from) {
        this.from = from;
    }

    public DestinationSimpleDTO getTo() {
        return to;
    }

    public void setTo(DestinationSimpleDTO to) {
        this.to = to;
    }
}


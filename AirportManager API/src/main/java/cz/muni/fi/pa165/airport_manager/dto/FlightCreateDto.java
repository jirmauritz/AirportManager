package cz.muni.fi.pa165.airport_manager.dto;

import java.util.Date;

/**
 * Created by User on 21. 11. 2015.
 */
public class FlightCreateDTO {

    private Boolean international;

    private Date departure;

    private Date arrival;

    private AirplaneDTO airplane;

    private DestinationSimpleDTO from;

    private DestinationSimpleDTO to;

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


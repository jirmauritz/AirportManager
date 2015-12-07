package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightSimpleDTO;
import cz.muni.fi.pa165.airport_manager.facade.FlightFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Rest controller for the flight entity.
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */

@RestController
@RequestMapping("/flights")
public class FlightRestController {

    @Autowired
    private FlightFacade flightFacade;

    @RequestMapping(value = "/flightdetail/{flightId}", method = RequestMethod.GET)
    FlightDTO getFlight(@PathVariable Long flightId) {
      return  flightFacade.getFlight(flightId);
    }

    @RequestMapping(value = "/flights", method = RequestMethod.GET)
    Collection<FlightSimpleDTO> getFlights() {
        return flightFacade.getFlights();
    }

    @RequestMapping(value = "/createFlight", method = RequestMethod.POST)
    Long createFlight(@RequestBody FlightCreateDTO flight) {
        return flightFacade.create(flight);
    }

    @RequestMapping(value = "/updateFlight", method = RequestMethod.PUT)
    void updateFlight(@RequestBody FlightSimpleDTO flight) {
        flightFacade.update(flight);
    }

    @RequestMapping(value = "/deleteFlight/{flightId}", method = RequestMethod.DELETE)
    void deleteFlight(@PathVariable Long flightId) {
        flightFacade.delete(flightId);
    }

    @RequestMapping(value = "/addSteward/{stewardId}/{flightId}", method = RequestMethod.PUT)
    void addSteward(@PathVariable Long stewardId, @PathVariable Long flightId) {
        flightFacade.addSteward(stewardId,flightId);
    }

    @RequestMapping(value = "/removeSteward/{stewardId}/{flightId}", method = RequestMethod.PUT)
    void removeSteward(@PathVariable Long stewardId, @PathVariable Long flightId) {
        flightFacade.removeSteward(stewardId,flightId);
    }
}

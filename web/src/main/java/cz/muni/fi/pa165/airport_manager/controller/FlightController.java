package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightSimpleDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardSimpleDTO;
import cz.muni.fi.pa165.airport_manager.entity.Flight;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import cz.muni.fi.pa165.airport_manager.facade.FlightFacade;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Provides the main airport manager interface.
 *
 * @author Jiri Mauritz <409972@mail.muni.cz>
 */
@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightFacade flightFacade;

    public void setCategoryFacade(FlightFacade categoryFacade) {
        this.flightFacade = categoryFacade;
    }

	/**
     * Shows all categories and products.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping("/list")
    public String list(Model model) {
		
        //get all flights
		Set<FlightSimpleDTO> flights = flightFacade.getFlights();
        model.addAttribute("flights", flights);

        //forward to /shopping/show.jsp
        return "flight/list";
    }

}

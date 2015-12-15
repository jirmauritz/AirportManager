package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.config.DataConfiguration;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightSimpleDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardSimpleDTO;
import cz.muni.fi.pa165.airport_manager.facade.AirplaneFacade;
import cz.muni.fi.pa165.airport_manager.facade.DestinationFacade;
import cz.muni.fi.pa165.airport_manager.facade.FlightFacade;
import cz.muni.fi.pa165.airport_manager.facade.StewardFacade;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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

	@Autowired
	private DestinationFacade destinationFacade;

	@Autowired
	private AirplaneFacade airplaneFacade;

	@Autowired
	private StewardFacade stewardFacade;

	public void setCategoryFacade(FlightFacade categoryFacade) {
		this.flightFacade = categoryFacade;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(AirplaneDTO.class, new AirplaneEditor(airplaneFacade));
		binder.registerCustomEditor(DestinationSimpleDTO.class, new DestinationEditor(destinationFacade));
	}

	/**
	 * Shows all flights.
	 *
	 * @param model data to display
	 * @return JSP page name
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String list(Model model) {

		// get all flights
		Set<FlightSimpleDTO> flights = flightFacade.getFlights();
		model.addAttribute("flights", flights);

		// forward to jsp
		return "flight/list";
	}

	/**
	 * Shows detail of one flight.
	 *
	 * @param id of the flight
	 * @param model data to display
	 * @return JSP page name
	 */
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String list(@PathVariable long id, Model model) {

		// get flight detail
		FlightDTO flight = flightFacade.getFlight(id);
		model.addAttribute("flight", flight);

		// forward to jsp
		return "flight/detail";
	}

	/**
	 * Prepares an empty form for creating flight.
	 *
	 * @param model data to display
	 * @return JSP page name
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String newCategory(Model model) {

		// prepare new flight, default values
		FlightCreateDTO flight = new FlightCreateDTO();
		flight.setDeparture(new Date());
		flight.setArrival(new Date());
		flight.setInternational(false);
		model.addAttribute("flightToCreate", flight);

		// prepare destinations as options
		Set<DestinationSimpleDTO> destinations = destinationFacade.findAll();
		model.addAttribute("destinations", convertDestinationsToStrings(destinations));
		// prepare airplanes as options
		Set<AirplaneDTO> airplanes = airplaneFacade.findAll();
		model.addAttribute("airplanes", convertAirplanesToStrings(airplanes));
		Set<StewardSimpleDTO> stewards = stewardFacade.getAllStewards();
		model.addAttribute("stewards", convertStewardsToStrings(stewards));

		return "flight/new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String create(@Valid @ModelAttribute("flightToCreate") FlightCreateDTO flight, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		//in case of validation error forward back to the the form
		if (bindingResult.hasErrors()) {
			for (ObjectError ge : bindingResult.getGlobalErrors()) {
			}
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
			}
			return "flight/new";
		}
		//create product
		Long id = flightFacade.create(flight);
		//report success
		redirectAttributes.addFlashAttribute("alert_success", "Flight " + id + " was created");
		return "redirect:" + uriBuilder.path("/flights/detail/" + id).toUriString();
	}
	
	private Set<String> convertAirplanesToStrings(Set<AirplaneDTO> airplanes) {
		Set<String> strings = new HashSet<> ();
		for (AirplaneDTO airplane : airplanes) {
			strings.add(airplane.getId() + 
					" name: " + airplane.getName() + 
					" type: " + airplane.getType() + 
					" capacity: " + airplane.getCapacity());
		}
		return strings;
	}
	
	private Set<String> convertDestinationsToStrings(Set<DestinationSimpleDTO> destinations) {
		Set<String> strings = new HashSet<> ();
		for (DestinationSimpleDTO destination : destinations) {
			strings.add(destination.getId() + 
					" name: " + destination.getName() + 
					" city: " + destination.getCity() + 
					" country: " + destination.getCountry());
		}
		return strings;
	}
	
	private Set<String> convertStewardsToStrings(Set<StewardSimpleDTO> stewards) {
		Set<String> strings = new HashSet<> ();
		for (StewardSimpleDTO steward : stewards) {
			strings.add(steward.getId() + " " + steward.getFirstName() +
					" " + steward.getLastName());
		}
		return strings;
	}
}

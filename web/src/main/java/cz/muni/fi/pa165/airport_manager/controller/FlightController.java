package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.config.DataConfiguration;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightDTO;
import cz.muni.fi.pa165.airport_manager.dto.FlightSimpleDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardDTO;
import cz.muni.fi.pa165.airport_manager.dto.StewardSimpleDTO;
import cz.muni.fi.pa165.airport_manager.facade.AirplaneFacade;
import cz.muni.fi.pa165.airport_manager.facade.DestinationFacade;
import cz.muni.fi.pa165.airport_manager.facade.FlightFacade;
import cz.muni.fi.pa165.airport_manager.facade.StewardFacade;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.orm.jpa.JpaSystemException;
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
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
	 * @param redirectAttributes
	 * @return JSP page name
	 */
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String detail(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {

		// get flight detail
		final FlightDTO flight = flightFacade.getFlight(id);

		// check if exists
		if (flight == null) {
			redirectAttributes.addFlashAttribute("warning", "No flight with id " + id + "exists.");
			return "redirect:/flights/list";
		}

		//assign to model
		model.addAttribute("flight", flight);

		// get all available stewards, than are not tied to the flight
		Set<StewardSimpleDTO> availableStewards = stewardFacade.getAllAvailable(flight.getDeparture(), flight.getArrival());
		availableStewards.removeAll(flight.getStewards());
		model.addAttribute("availableStewards", availableStewards);

		// get all available airplanes
		Set<AirplaneDTO> availableAirplanes = airplaneFacade.getAllAvailable(flight.getDeparture(), flight.getArrival());
		model.addAttribute("availableAirplanes", availableAirplanes);

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
	public String newFlight(Model model) {

		// prepare new flight, default values
		FlightCreateDTO flight = new FlightCreateDTO();
		flight.setDeparture(new Date());
		flight.setArrival(new Date());
		flight.setInternational(true);

		model.addAttribute("flightToCreate", flight);

		// prepare destinations as options
		Set<DestinationSimpleDTO> destinations = destinationFacade.findAll();
		model.addAttribute("destinations", convertDestinationsToStrings(destinations));

		return "flight/new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String create(@ModelAttribute("flightToCreate") FlightCreateDTO flight,
			Model model, RedirectAttributes redirectAttributes) {
		// check dates
		if (flight.getDeparture().after(flight.getArrival()) || flight.getDeparture().equals(flight.getArrival())) {
			redirectAttributes.addFlashAttribute("error", "Departure of the flight must be before the arrival.");
			return "redirect:/flights/new";
		}
		//create flight
		Long id = null;
		try {
			id = flightFacade.create(flight);
		} catch (IllegalArgumentException ex) {
			// report error
			redirectAttributes.addFlashAttribute("error", ex.getMessage());
		}
		//report success
		redirectAttributes.addFlashAttribute("success", "Flight " + id + " was created");
		return "redirect:/flights/detail/" + id;
	}

	/**
	 * Prepares prefilled form for updating flight.
	 *
	 * @param id of the flight
	 * @param model data to display
	 * @return JSP page name
	 */
	@RequestMapping(value = "/updating/{id}", method = RequestMethod.GET)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String updatingFlight(@PathVariable long id, Model model) {

		// fetch flight from db for updating
		FlightSimpleDTO flight = flightFacade.getFlight(id);

		model.addAttribute("flightToUpdate", flight);

		// prepare destinations as options
		Set<DestinationSimpleDTO> destinations = destinationFacade.findAll();
		model.addAttribute("destinations", convertDestinationsToStrings(destinations));

		return "flight/updating";
	}

	/**
	 * Updates the flight.
	 *
	 * @param id of the flight
	 * @param flight updated flight
	 * @param model data to display
	 * @param redirectAttributes
	 * @return JSP page name
	 */
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String updateFlight(@PathVariable long id, @ModelAttribute("flightToUpdate") FlightSimpleDTO flight,
			Model model, RedirectAttributes redirectAttributes) {
		// check dates
		if (flight.getDeparture().after(flight.getArrival()) || flight.getDeparture().equals(flight.getArrival())) {
			redirectAttributes.addFlashAttribute("error", "Departure of the flight must be before the arrival.");
			return "redirect:/flights/updating/" + id;
		}
		// update flight
		try {
			// keep airplane of the flight
			flight.setAirplane(flightFacade.getFlight(id).getAirplane());
			// update
			flightFacade.update(flight);
		} catch (IllegalArgumentException ex) {
			// report error
			redirectAttributes.addFlashAttribute("error", ex.getMessage());
		}
		//report success
		redirectAttributes.addFlashAttribute("success", "Flight " + id + " was successfuly updated.");
		return "redirect:/flights/detail/" + id;
	}

	/**
	 * Delete flight by id
	 *
	 * @param id flight id
	 * @param model displayed data
	 * @param redirectAttributes redirected attributes
	 * @param uriBuilder url builder
	 * @return redirection according to action result
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,
			UriComponentsBuilder uriBuilder) {

		// First check, if flight exists
		FlightDTO flight;
		try {
			flight = flightFacade.getFlight(id);
		} catch (MappingException e) {
			redirectAttributes.addFlashAttribute("error", "Flight with id " + id
					+ " does not exist.");
			return "redirect:/flights/list";
		}

		// Check if there are stewards tied to flight
		if (! flight.getStewards().isEmpty()) {
			redirectAttributes.addFlashAttribute("error", "Error while deleting flight "
					+ "with id " + id + ": stewards are still tied to the flight.");
			return "redirect:/flights/list";
		}
		 
		// Try to delete flight
		try {
			flightFacade.delete(id);
		} catch (JpaSystemException e) {
			redirectAttributes.addFlashAttribute("error", "Error while deleting flight "
					+ "with id " + id + ".");
			return "redirect:/flights/list";
		}

		// Report success
		redirectAttributes.addFlashAttribute("success", "Flight with id " + id
				+ " successfully deleted.");
		return "redirect:/flights/list";
	}

	/**
	 * Remove steward from flight
	 *
	 * @param flightId id of the flight
	 * @param stewardId id of the steward
	 * @param model displayed data
	 * @param redirectAttributes redirected attributes
	 * @return redirection according to action result
	 */
	@RequestMapping(value = "/{flightId}/removeSteward/{stewardId}", method = RequestMethod.POST)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String removeStewardFromFlight(@PathVariable long flightId, @PathVariable long stewardId, Model model, RedirectAttributes redirectAttributes) {

		// First check, if flight exists
		FlightDTO flight;
		try {
			flight = flightFacade.getFlight(flightId);
		} catch (MappingException e) {
			redirectAttributes.addFlashAttribute("error", "Flight with id " + flightId
					+ " does not exist.");
			return "redirect:/flights/detail/" + flightId;
		}

		// Check, if the steward exists
		StewardDTO steward;
		try {
			steward = stewardFacade.getSteward(stewardId);
		} catch (MappingException e) {
			redirectAttributes.addFlashAttribute("error", "Steward with id " + stewardId
					+ " does not exist.");
			return "redirect:/flights/detail/" + flightId;
		}

		// Try to remove steward from flight
		try {
			flightFacade.removeSteward(stewardId, flightId);
		} catch (JpaSystemException e) {
			redirectAttributes.addFlashAttribute("error", "Error while removing steward id " + stewardId + " from flight "
					+ "with id " + flightId + ".");
			return "redirect:/flights/detail/" + flightId;
		}

		return "redirect:/flights/detail/" + flightId;
	}

	/**
	 * Add steward to flight
	 *
	 * @param flightId id of the flight
	 * @param stewardId id of the steward
	 * @param model displayed data
	 * @param redirectAttributes redirected attributes
	 * @return redirection according to action result
	 */
	@RequestMapping(value = "/{flightId}/addSteward/{stewardId}", method = RequestMethod.POST)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String addStewardToFlight(@PathVariable long flightId, @PathVariable long stewardId, Model model, RedirectAttributes redirectAttributes) {

		// First check, if flight exists
		FlightDTO flight;
		try {
			flight = flightFacade.getFlight(flightId);
		} catch (MappingException e) {
			redirectAttributes.addFlashAttribute("error", "Flight with id " + flightId
					+ " does not exist.");
			return "redirect:/flights/detail/" + flightId;
		}

		// Check, if the steward exists
		StewardDTO steward;
		try {
			steward = stewardFacade.getSteward(stewardId);
		} catch (MappingException e) {
			redirectAttributes.addFlashAttribute("error", "Steward with id " + stewardId
					+ " does not exist.");
			return "redirect:/flights/detail/" + flightId;
		}

		// Try to add steward to flight
		try {
			flightFacade.addSteward(stewardId, flightId);
		} catch (JpaSystemException e) {
			redirectAttributes.addFlashAttribute("error", "Error while adding steward id " + stewardId + " to flight "
					+ "with id " + flightId + ".");
			return "redirect:/flights/detail/" + flightId;
		}

		return "redirect:/flights/detail/" + flightId;
	}

	/**
	 * Add airplane to flight
	 *
	 * @param flightId id of the flight
	 * @param airplaneId id of the airplane
	 * @param model displayed data
	 * @param redirectAttributes redirected attributes
	 * @return redirection according to action result
	 */
	@RequestMapping(value = "/{flightId}/addAirplane/{airplaneId}", method = RequestMethod.POST)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String addAirplaneToFlight(@PathVariable long flightId, @PathVariable long airplaneId, Model model, RedirectAttributes redirectAttributes) {

		// First check, if flight exists
		FlightDTO flight;
		try {
			flight = flightFacade.getFlight(flightId);
		} catch (MappingException e) {
			redirectAttributes.addFlashAttribute("error", "Flight with id " + flightId
					+ " does not exist.");
			return "redirect:/flights/detail/" + flightId;
		}

		// Check, if the steward exists
		AirplaneDTO airplane;
		try {
			airplane = airplaneFacade.getAirplane(airplaneId);
		} catch (MappingException e) {
			redirectAttributes.addFlashAttribute("error", "Airplane with id " + airplaneId
					+ " does not exist.");
			return "redirect:/flights/detail/" + flightId;
		}

		// Try to add airplane to flight
		try {
			flight.setAirplane(airplane);
			flightFacade.update(flight);
		} catch (JpaSystemException e) {
			redirectAttributes.addFlashAttribute("error", "Error while adding airplane id " + airplaneId + " to flight "
					+ "with id " + flightId + ".");
			return "redirect:/flights/detail/" + flightId;
		}

		return "redirect:/flights/detail/" + flightId;
	}

	/**
	 * Remove airplane from flight
	 *
	 * @param flightId id of the flight
	 * @param model displayed data
	 * @param redirectAttributes redirected attributes
	 * @return redirection according to action result
	 */
	@RequestMapping(value = "/{flightId}/removeAirplane", method = RequestMethod.POST)
	@Secured(value = DataConfiguration.ROLE_FLIGHT)
	public String removeAirplaneFromFlight(@PathVariable long flightId, Model model, RedirectAttributes redirectAttributes) {

		// First check, if flight exists
		FlightDTO flight;
		try {
			flight = flightFacade.getFlight(flightId);
		} catch (MappingException e) {
			redirectAttributes.addFlashAttribute("error", "Flight with id " + flightId
					+ " does not exist.");
			return "redirect:/flights/detail/" + flightId;
		}

		// Try to remove airplane from flight
		try {
			flight.setAirplane(null);
			flightFacade.update(flight);
		} catch (JpaSystemException e) {
			redirectAttributes.addFlashAttribute("error", "Error while removing airplane from flight "
					+ "with id " + flightId + ".");
			return "redirect:/flights/detail/" + flightId;
		}

		return "redirect:/flights/detail/" + flightId;
	}

	private Set<String> convertAirplanesToStrings(Set<AirplaneDTO> airplanes) {
		Set<String> strings = new HashSet<>();
		for (AirplaneDTO airplane : airplanes) {
			strings.add(airplane.getId()
					+ " name: " + airplane.getName()
					+ " type: " + airplane.getType()
					+ " capacity: " + airplane.getCapacity());
		}
		return strings;
	}

	private Set<String> convertDestinationsToStrings(Set<DestinationSimpleDTO> destinations) {
		Set<String> strings = new HashSet<>();
		for (DestinationSimpleDTO destination : destinations) {
			strings.add(destination.getId()
					+ " name: " + destination.getName()
					+ " city: " + destination.getCity()
					+ " country: " + destination.getCountry());
		}
		return strings;
	}

	private Set<String> convertStewardsToStrings(Set<StewardSimpleDTO> stewards) {
		Set<String> strings = new HashSet<>();
		for (StewardSimpleDTO steward : stewards) {
			strings.add(steward.getId() + " " + steward.getFirstName()
					+ " " + steward.getLastName());
		}
		return strings;
	}
}

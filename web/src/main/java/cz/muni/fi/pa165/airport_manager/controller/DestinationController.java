package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.config.DataConfiguration;
import cz.muni.fi.pa165.airport_manager.dto.DestinationCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;
import cz.muni.fi.pa165.airport_manager.facade.DestinationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

/**
 * Spring MVC controller for the destination entity.
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
@Controller
@RequestMapping("/destinations")
public class DestinationController {

    @Autowired
    private DestinationFacade destinationFacade;


    public void setDestinationFacade(DestinationFacade destinationFacade) {
        this.destinationFacade = destinationFacade;
    }

    /**
     * Shows all destinations.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        // get all destinations
        Set<DestinationSimpleDTO> destinations = destinationFacade.findAll();
        model.addAttribute("destinations", destinations);

        // forward to jsp
        return "destination/list";
    }

    /**
     * Shows detail of one destination.
     *
     * @param id    of the flight
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String list(@PathVariable long id, Model model) {

        // get destination detail
        DestinationSimpleDTO destination = destinationFacade.findById(id);
        model.addAttribute("destination", destination);
        model.addAttribute("flights", destinationFacade.getFlightsByDestinations(id));

        // forward to jsp
        return "destination/detail";
    }

    /**
     * Prepares an empty form for creating destination.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String newDestination(Model model) {
        DestinationCreateDTO destinationDto = new DestinationCreateDTO();
        model.addAttribute("destinationToCreate", destinationDto);
        return "destination/new";
    }

    /**
     * Create new destination from mapped form values
     *
     * @param destination to be created
     * @param redirectAttributes redirected attributes
     * @return redirection according to action result
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String create(@Valid @ModelAttribute("destinationToCreate") DestinationCreateDTO destination, Model model, RedirectAttributes redirectAttributes) {

        if (isValid(destination)) {
            Long id = destinationFacade.create(destination);
            //report success
            redirectAttributes.addFlashAttribute("alert_success", "Destination " + destination.getName() + " was created");
            return "redirect:/destinations/detail/" + id;
        } else {
            model.addAttribute("warning", "Destination inputs are not correct");
            model.addAttribute("destinationToCreate", destination);
            return "destination/new";
        }
    }

    /**
     * Create new airplane from mapped form values
     *
     * @param id                 airplane id
     * @param redirectAttributes redirected attributes
     * @return redirection according to action result
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @Secured(DataConfiguration.ROLE_AIRPORT)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {

       if (!destinationFacade.getFlightsByDestinations(id).isEmpty()){
           redirectAttributes.addFlashAttribute("error", "There is flight assign to destination "
                   + "with id " + id + ".");
           return "redirect:/destinations/list";
       }

        destinationFacade.delete(id);
        redirectAttributes.addFlashAttribute("success", "Destination with id " + id
                + " successfully deleted.");
        return "redirect:/destinations/list";
    }

    /**
     * Prepares prefilled form for updating destination.
     *
     * @param id of the destination
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/updating/{id}", method = RequestMethod.GET)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String updatingDestination(@PathVariable long id, Model model) {

        // fetch flight from db for updating
        DestinationSimpleDTO destination = destinationFacade.findById(id);

        model.addAttribute("destinationToUpdate", destination);

        return "destination/updating";
    }
    /**
     * Updates the destination.
     *
     * @param id of the destination
     * @param destination updated destination
     * @param redirectAttributes
     * @return JSP page name
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String updateDestination(@PathVariable long id, @ModelAttribute("destinationToUpdate") DestinationSimpleDTO destination, RedirectAttributes redirectAttributes, Model model) {

        if (!isValid(destination)) {
            model.addAttribute("warning", "Destination inputs are not correct");
            model.addAttribute("destinationToUpdate", destination);
            return "destination/updating";
        }

        destinationFacade.update(destination);
        redirectAttributes.addFlashAttribute("success", "Destination " + id + " was successfuly updated.");
        return "redirect:/destinations/detail/" + id;
    }

    private static boolean isValid(final DestinationCreateDTO destination) {
        if (destination.getCity().isEmpty()) return false;
        if (destination.getCountry().isEmpty()) return false;
        if (destination.getName().isEmpty()) return false;

        return destination.getName().matches("\\p{L}+(?:\\s\\p{L}+)*") && destination.getCountry().matches("\\p{L}+(?:\\s\\p{L}+)*") && destination.getCity().matches("\\p{L}+(?:\\s\\p{L}+)*");
    }

}

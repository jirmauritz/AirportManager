package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.config.DataConfiguration;
import cz.muni.fi.pa165.airport_manager.dto.DestinationCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.DestinationSimpleDTO;
import cz.muni.fi.pa165.airport_manager.facade.DestinationFacade;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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
     * @param model data to display
     * @param redirectAttributes redirected attributes
     * @return redirection according to action result
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String create(@Valid @ModelAttribute("destinationToCreate") DestinationCreateDTO destination,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        //create product
        Long id = destinationFacade.create(destination);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Destination " + destination.getName() + " was created");
        return "redirect:/destinations/detail/" + id;
    }

    /**
     * Create new airplane from mapped form values
     *
     * @param id                 airplane id
     * @param redirectAttributes redirected attributes
     * @return redirection according to action result
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Secured(DataConfiguration.ROLE_AIRPORT)
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {

        DestinationSimpleDTO destination;
        try {
            destination = destinationFacade.findById(id);
        } catch (MappingException e) {
            redirectAttributes.addFlashAttribute("error", "Destination with id " + id
                    + " does not exist.");
            return "redirect:/destinations/list";
        }


        try {
            destinationFacade.delete(id);
        } catch (JpaSystemException e) {
            redirectAttributes.addFlashAttribute("error", "There is flight assign to destination "
                    + "with id " + id + ".");
            return "redirect:/destinations/list";
        }

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
     * @param model data to display
     * @param redirectAttributes
     * @return JSP page name
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String updateDestination(@PathVariable long id, @ModelAttribute("destinationToUpdate") DestinationSimpleDTO destination,
                               Model model, RedirectAttributes redirectAttributes) {

        // update flight
        try {
            destinationFacade.update(destination);
        } catch (IllegalArgumentException ex) {
            // report error
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        //report success
        redirectAttributes.addFlashAttribute("success", "Destination " + id + " was successfuly updated.");
        return "redirect:/destinations/detail/" + id;
    }

}

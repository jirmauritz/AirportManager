package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.config.DataConfiguration;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import cz.muni.fi.pa165.airport_manager.exception.AirportManagerDataAccessException;
import cz.muni.fi.pa165.airport_manager.facade.AirplaneFacade;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
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

/**
 * Controller for the airplane page
 *
 * @author Dušan Lago
 * @author 396336@mail.muni.cz
 */
@Controller
@RequestMapping("/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneFacade airplaneFacade;

    public void setCategoryFacade(AirplaneFacade categoryFacade) {
        this.airplaneFacade = categoryFacade;
    }

    /**
     * Show all airplanes.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Secured(value = {DataConfiguration.ROLE_FLIGHT, DataConfiguration.ROLE_AIRPORT})
    public String list(Model model) {

        // get all airplanes
        Set<AirplaneDTO> airplanes = airplaneFacade.findAll();
        model.addAttribute("airplanes", airplanes);

        // forward to jsp
        return "airplane/list";
    }

    /**
     * Show blank page with action results
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/action", method = RequestMethod.GET)
    @Secured(value = {DataConfiguration.ROLE_FLIGHT, DataConfiguration.ROLE_AIRPORT})
    public String action(Model model) {
        return "airplane/action";
    }

    /**
     * Prepare empty airplane with airplane types
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String newCategory(Model model) {

        // prepare new flight, default values
        AirplaneCreateDTO airplane = new AirplaneCreateDTO();

        // Load all airplane types
        List<String> types = new ArrayList<String>();

        for (AirplaneType t : AirplaneType.values()) {
            types.add(t.toString().toUpperCase());
        }

        model.addAttribute("airplaneTypes", types);
        model.addAttribute("airplaneToCreate", airplane);

        return "airplane/new";
    }

    /**
     * Create new airplane from mapped form values
     * @param airplane to be created
     * @param model to be displayed
     * @param redirectAttributes redirected attributes
     * @param uriBuilder
     * @return redirection according to action result
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String create(@Valid @ModelAttribute("airplaneToCreate") AirplaneCreateDTO airplane,
            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

        // Try to create airplane
        Long id;
        try {
            id = airplaneFacade.createAirplane(airplane);
        } catch (AirportManagerDataAccessException e) {
            redirectAttributes.addFlashAttribute("error", "Airplane with name " + airplane.getName()
                    + " already exists.");
            return "redirect:" + uriBuilder.path("/airplanes/action").toUriString();
        }

        // Report success
        redirectAttributes.addFlashAttribute("success", "Airplane " + airplane.getName()
                + " successfully created with id " + id + ".");
        return "redirect:" + uriBuilder.path("/airplanes/action").toUriString();
    }

    /**
     * Create new airplane from mapped form values
     * @param id airplane id
     * @param model displayed data
     * @param redirectAttributes redirected attributes
     * @param uriBuilder
     * @return redirection according to action result
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        // First check, if airplane exists
        AirplaneDTO airplane;
        try {
            airplane = airplaneFacade.getAirplane(id);
        } catch (MappingException e) {
            redirectAttributes.addFlashAttribute("error", "Airplane with id " + id
                    + " does not exist.");
            return "redirect:" + uriBuilder.path("/airplanes/action").toUriString();
        }

        // Try to delete airplane
        try {
            airplaneFacade.deleteAirplaneById(id);
        } catch (JpaSystemException e) {
            redirectAttributes.addFlashAttribute("warning", "Cannot remove airplane with id " + id
                    + ". It is still used by some flight.");
            return "redirect:" + uriBuilder.path("/airplanes/action").toUriString();
        }

        // Report success
        redirectAttributes.addFlashAttribute("success", "Airplane " + airplane.getName()
                + " with id " + id + " successfully deleted.");
        return "redirect:" + uriBuilder.path("/airplanes/action").toUriString();
    }
}

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
    public String list(Model model) {

        // get all airplanes
        Set<AirplaneDTO> airplanes = airplaneFacade.findAll();
        model.addAttribute("airplanes", airplanes);

        // forward to jsp
        return "airplane/list";
    }

    /**
     * Show airplanes detail.
     *
     * @param id airplane id
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        final AirplaneDTO airplaneDTO = airplaneFacade.getAirplane(id);
        if (airplaneDTO == null) {
            redirectAttributes.addFlashAttribute("warning", "No airplane with id " + id + "exists.");
            return "redirect:/airplanes/list";
        }

        model.addAttribute("airplane", airplaneDTO);
        return "airplane/detail";
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
        AirplaneCreateDTO airplane = new AirplaneCreateDTO();

        List<String> types = new ArrayList<>();
        for (AirplaneType t : AirplaneType.values()) {
            types.add(t.toString().toUpperCase());
        }

        model.addAttribute("airplaneTypes", types);
        model.addAttribute("airplaneToCreate", airplane);

        return "airplane/new";
    }

    /**
     * Load airplane and prefill form
     *
     * @param id airplanes id
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/updating/{id}", method = RequestMethod.GET)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String updating(@PathVariable long id, Model model) {

        // Load airplane
        AirplaneDTO airplane = airplaneFacade.getAirplane(id);

        List<String> types = new ArrayList<>();
        for (AirplaneType t : AirplaneType.values()) {
            types.add(t.toString().toUpperCase());
        }

        model.addAttribute("airplaneTypes", types);
        model.addAttribute("airplaneToUpdate", airplane);

        return "airplane/updating";
    }

    /**
     * Update airplane
     *
     * @param id airplanes id
     * @param airplane updated airplane
     * @param redirectAttributes
     * @return JSP page name
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String update(@PathVariable long id, @ModelAttribute("airplaneToUpdate") AirplaneDTO airplane,
            RedirectAttributes redirectAttributes) {

        //Test name
        if (!isValid(airplane.getName())) {
            redirectAttributes.addFlashAttribute("error", "Airplane was not updated. "
                    + "Name " + airplane.getName() + " is invalid.");
            return "redirect:/airplanes/new";
        }

        // Update airplane
        try {
            airplaneFacade.update(airplane);
        } catch (IllegalArgumentException ex) {
            // Report error
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/airplanes/list";
        }

        // Report success
        redirectAttributes.addFlashAttribute("success", "Airplane with " + id
                + " successfuly updated.");
        return "redirect:/airplanes/detail/" + id;
    }

    /**
     * Create new airplane from mapped form values
     *
     * @param airplane to be created
     * @param model to be displayed
     * @param redirectAttributes redirected attributes
     * @return redirection according to action result
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String create(@Valid @ModelAttribute("airplaneToCreate") AirplaneCreateDTO airplane,
            Model model, RedirectAttributes redirectAttributes) {

        //Test name
        if (!isValid(airplane.getName())) {
            redirectAttributes.addFlashAttribute("error", "Airplane was not created. "
                    + "Name " + airplane.getName() + " is invalid.");
            return "redirect:/airplanes/new";
        }

        // Try to create airplane      
        Long id;
        try {
            id = airplaneFacade.createAirplane(airplane);
        } catch (AirportManagerDataAccessException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/airplanes/list";
        }

        // Report success
        redirectAttributes.addFlashAttribute("success", "Airplane " + airplane.getName()
                + " successfully created with id " + id + ".");
        return "redirect:/airplanes/detail/" + id;
    }

    /**
     * Delete airplane by id
     *
     * @param id airplane id
     * @param model displayed data
     * @param redirectAttributes redirected attributes
     * @return redirection according to action result
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {

        // Check, if airplane exists
        AirplaneDTO airplane = airplaneFacade.getAirplane(id);;

        if (airplane == null) {
            redirectAttributes.addFlashAttribute("error", "Airplane with id " + id
                    + " does not exist.");
            return "redirect:/airplanes/list";
        }

        // Try to delete airplane
        try {
            airplaneFacade.deleteAirplaneById(id);
        } catch (JpaSystemException e) {
            redirectAttributes.addFlashAttribute("warning", "Cannot remove airplane with id " + id
                    + ". It is still used by some flight.");
            return "redirect:/airplanes/list";
        }
        // Report success
        redirectAttributes.addFlashAttribute("success", "Airplane " + airplane.getName()
                + " with id " + id + " successfully deleted.");
        return "redirect:/airplanes/list";
    }

    private static boolean isValid(final String name) {
        if (name.isEmpty()) {
            return false;
        }
        return name.matches("(?:\\s+\\p{L})+");
    }
}

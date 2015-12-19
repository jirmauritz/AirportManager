package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.config.DataConfiguration;
import cz.muni.fi.pa165.airport_manager.dto.*;
import cz.muni.fi.pa165.airport_manager.facade.AirplaneFacade;
import cz.muni.fi.pa165.airport_manager.facade.DestinationFacade;
import cz.muni.fi.pa165.airport_manager.facade.FlightFacade;
import cz.muni.fi.pa165.airport_manager.facade.StewardFacade;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Provides the main steward manager interface.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Controller
@RequestMapping("/stewards")
public class StewardController {

    private static final String PATH_PREFIX = "/steward";

	@Autowired
	private FlightFacade flightFacade;

	@Autowired
	private StewardFacade stewardFacade;

    /**
     * All stewards
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception {
        model.addAttribute("stewards", stewardFacade.getAllStewards());
        return PATH_PREFIX + "/list";
    }

    /**
     * Shows detail of one steward.
     *
     * @param id of the steward
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String list(@PathVariable long id, Model model) {
        model.addAttribute("steward", stewardFacade.getSteward(id));
        return PATH_PREFIX + "/detail";
    }

    /**
     * Create new airplane from mapped form values
     * @param id airplane id
     * @param model displayed data
     * @param redirectAttributes redirected attributes
     * @param uriBuilder url builder
     * @return redirection according to action result
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes,
                         UriComponentsBuilder uriBuilder) {

        StewardDTO steward;
        try {
            steward = stewardFacade.getSteward(id);
        } catch (MappingException e) {
            redirectAttributes.addFlashAttribute("error", "Steward with id " + id
                    + " does not exist.");
            return "redirect:/action";
        }

        // Try to delete airplane
        try {
            stewardFacade.deleteSteward(id);
        } catch (JpaSystemException e) {
            redirectAttributes.addFlashAttribute("warning", "Cannot remove steward with id " + id
                    + ". It is still assigned to some flight.");
            return "redirect:/action";
        }

        // Report success
        redirectAttributes.addFlashAttribute("success", "Steward " + steward.getFirstName() + ' '
                + steward.getLastName() + " with id " + id + " successfully deleted.");
        return "redirect:/action";
    }

}

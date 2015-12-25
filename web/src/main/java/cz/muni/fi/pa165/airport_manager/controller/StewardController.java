package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.config.DataConfiguration;
import cz.muni.fi.pa165.airport_manager.dto.*;
import cz.muni.fi.pa165.airport_manager.facade.StewardFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Provides the main steward manager interface.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Controller
@RequestMapping("/stewards")
public class StewardController {

    private static final String VIEW_PREFIX = "/steward";

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
        return VIEW_PREFIX + "/list";
    }

    /**
     * Shows detail of one steward.
     *
     * @param id of the steward
     * @param model data to display
     * @param redirectAttributes redirected attributes
     * @return JSP page name
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        final StewardDTO stewardDTO = stewardFacade.getSteward(id);
        if (stewardDTO == null) {
            redirectAttributes.addFlashAttribute("warning", "No steward with id " + id + " exists.");
            return "redirect:/stewards/list";
        }

        model.addAttribute("steward", stewardDTO);
        return VIEW_PREFIX + "/detail";
    }

    /**
     * Prepare empty steward and return new page
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    @Secured(DataConfiguration.ROLE_AIRPORT)
    public String newSteward(Model model) {
        final StewardCreateDTO stewardCreateDTO = new StewardCreateDTO();
        model.addAttribute("stewardDTO", stewardCreateDTO);
        return VIEW_PREFIX + "/new";
    }

    /**
     * Shows update page of one steward.
     *
     * @param id of the steward
     * @param model data to display
     * @param redirectAttributes redirected attributes
     * @return JSP page name
     */
    @RequestMapping(value = "/new/{id}", method = RequestMethod.GET)
    @Secured(DataConfiguration.ROLE_AIRPORT)
    public String update(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        final StewardDTO stewardDTO = stewardFacade.getSteward(id);
        if (stewardDTO == null) {
            redirectAttributes.addFlashAttribute("warning", "No steward with id " + id + " exists.");
            return "redirect:/stewards/list";
        }
        model.addAttribute("stewardDTO", stewardDTO);
        model.addAttribute("stewardId", Long.toString(id));
        return VIEW_PREFIX + "/new";
    }

    /**
     * Create new steward from mapped form values
     *
     * @param stewardCreateDTO to be created
     * @param model data to display
     * @param redirectAttributes redirected attributes
     * @return redirection according to action result
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String create(@Valid @ModelAttribute("stewardDTO") StewardCreateDTO stewardCreateDTO,
                         Model model, RedirectAttributes redirectAttributes) {
        if (!isValid(stewardCreateDTO)) {
            model.addAttribute("warning", "Steward names are not correct");
            model.addAttribute("stewardDTO", stewardCreateDTO);
            return VIEW_PREFIX + "/new";
        }

        final Long id = stewardFacade.createSteward(stewardCreateDTO);
        redirectAttributes.addFlashAttribute("success", "Steward " +
                stewardCreateDTO.getFirstName() + ' ' + stewardCreateDTO.getLastName() +
                " successfully created with id " + id + ".");
        return "redirect:/stewards/detail/" + Long.toString(id);
    }

    /**
     * Update steward from mapped form values
     *
     * @param stewardCreateDTO to be updated
     * @param id of the steward
     * @param model data to display
     * @param redirectAttributes redirected attributes
     * @return redirection according to action result
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @Secured(value = DataConfiguration.ROLE_AIRPORT)
    public String update(@Valid @ModelAttribute("stewardDTO") StewardCreateDTO stewardCreateDTO,
                         @PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        if (!isValid(stewardCreateDTO)) {
            model.addAttribute("warning", "Steward names are not correct");
            model.addAttribute("stewardDTO", stewardCreateDTO);
            model.addAttribute("stewardId", Long.toString(id));
            return VIEW_PREFIX + "/new";
        }
        stewardFacade.updateNames(id, stewardCreateDTO.getFirstName(), stewardCreateDTO.getLastName());
        redirectAttributes.addFlashAttribute("success", "Steward " +
                stewardCreateDTO.getFirstName() + ' ' + stewardCreateDTO.getLastName() +
                "with id " + id + " successfully updated.");
        return "redirect:/stewards/detail/" + id;
    }

    /**
     * Delete steward
     *
     * @param id steward id
     * @param redirectAttributes redirected attributes
     * @return redirection according to action result
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @Secured(DataConfiguration.ROLE_AIRPORT)
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        final StewardDTO steward = stewardFacade.getSteward(id);

        if (steward == null) {
            redirectAttributes.addFlashAttribute("warning", "No steward with id " + id + " exists.");
            return "redirect:/stewards/list";
        }

        if (!steward.getFlights().isEmpty()) {
            redirectAttributes.addFlashAttribute("warning", "Cannot remove steward " +
                    steward.getFirstName() + ' ' + steward.getLastName() +
                    " with id " + id + ". It is still assigned to some flight.");
            return "redirect:/stewards/detail/" + id;
        }

        stewardFacade.deleteSteward(id);

        redirectAttributes.addFlashAttribute("success", "Steward " + steward.getFirstName() + ' '
                + steward.getLastName() + " with id " + id + " successfully deleted.");
        return "redirect:/stewards/list";
    }

    private static boolean isValid(final StewardCreateDTO steward) {
        if (steward.getFirstName().isEmpty()) return false;
        if (steward.getLastName().isEmpty()) return false;
        return  steward.getFirstName().matches("\\p{L}+(?:\\s\\p{L}+)*") &&
                steward.getLastName() .matches("\\p{L}+(?:\\s\\p{L}+)*");
    }

}

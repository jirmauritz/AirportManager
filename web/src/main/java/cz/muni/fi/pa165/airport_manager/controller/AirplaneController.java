package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.config.DataConfiguration;
import cz.muni.fi.pa165.airport_manager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airport_manager.facade.AirplaneFacade;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}

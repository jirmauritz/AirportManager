package cz.muni.fi.pa165.airport_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Default URL
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Controller
@RequestMapping("/")
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultView() {
        return "redirect:/flights/list";
    }

}

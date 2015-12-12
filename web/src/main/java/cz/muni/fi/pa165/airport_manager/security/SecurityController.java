package cz.muni.fi.pa165.airport_manager.security;

import cz.muni.fi.pa165.airport_manager.security.SecurityConfiguration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Example Security controller
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Controller
@RequestMapping("/")
public class SecurityController {

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String loginPage (HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }
    
}

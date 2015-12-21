package cz.muni.fi.pa165.airport_manager.controller;

import cz.muni.fi.pa165.airport_manager.exception.AirportManagerDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * General exception handling
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(AirportManagerDataAccessException.class)
    public String databaseError() {
        return "/error/900";
    }

    @ExceptionHandler(Exception.class)
    public String anotherError() {
        return "/error/500";
    }

}

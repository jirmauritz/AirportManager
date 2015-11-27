package cz.muni.fi.pa165.airport_manager.exception;

/**
 * Implementation of {@link org.springframework.dao.DataAccessException}
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public class AirportManagerDataAccessException extends org.springframework.dao.DataAccessException{
    public AirportManagerDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AirportManagerDataAccessException(String msg) {
        super(msg);
    }
}

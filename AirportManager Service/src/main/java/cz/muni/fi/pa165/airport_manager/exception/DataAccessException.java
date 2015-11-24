package cz.muni.fi.pa165.airport_manager.exception;

/**
 * Implementation of {@link org.springframework.dao.DataAccessException}
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public class DataAccessException extends org.springframework.dao.DataAccessException{
    public DataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public DataAccessException(String msg) {
        super(msg);
    }
}

package cz.muni.fi.pa165.airport_manager.service;

import java.util.Collection;
import java.util.Set;

/**
 * Service for object mapping.
 *
 * @author Lenka Heldova
 * @author 422578@mail.muni.cz
 */
public interface MappingService {
    <T> Set<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);
}

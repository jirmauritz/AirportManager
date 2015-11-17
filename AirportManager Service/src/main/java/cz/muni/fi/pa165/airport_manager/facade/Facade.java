package cz.muni.fi.pa165.airport_manager.facade;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

/**
 * Annotation to annotate facade implementations with {@link Service} and {@link Transactional}
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

@Service
@Transactional
public @interface Facade {
}

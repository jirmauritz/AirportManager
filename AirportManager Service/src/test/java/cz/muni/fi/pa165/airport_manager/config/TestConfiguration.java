package cz.muni.fi.pa165.airport_manager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for tests
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Configuration
@ComponentScan(basePackages = {"cz.muni.fi.pa165.airport_manager.service", "cz.muni.fi.pa165.airport_manager.facade"})
public class TestConfiguration {
}

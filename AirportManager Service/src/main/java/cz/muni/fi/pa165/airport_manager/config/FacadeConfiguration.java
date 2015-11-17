package cz.muni.fi.pa165.airport_manager.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring configuration of the facade layer.
 * The configuration uses spring context of the service layer.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */

@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.airport_manager.facade")
public class FacadeConfiguration {

}
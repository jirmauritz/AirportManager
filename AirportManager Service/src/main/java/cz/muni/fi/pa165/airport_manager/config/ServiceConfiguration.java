package cz.muni.fi.pa165.airport_manager.config;

import cz.muni.fi.pa165.airport_manager.service.DestinationService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Spring configuration of the service layer.
 * The configuration uses spring context of the persistence layer.
 * 
 * @author Jiri Mauritz
 * @author 409972@mail.muni.cz
 */

@Configuration
@Import(EmbeddedPersistenceContext.class)
@ComponentScan(basePackageClasses={DestinationService.class}) //, DestinationFacade.class})
public class ServiceConfiguration {
	
}

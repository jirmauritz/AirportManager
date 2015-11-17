package cz.muni.fi.pa165.airport_manager.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
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
@Import(EmbeddedPersistenceConfiguration.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.airport_manager.service")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        return new DozerBeanMapper();
    }
}

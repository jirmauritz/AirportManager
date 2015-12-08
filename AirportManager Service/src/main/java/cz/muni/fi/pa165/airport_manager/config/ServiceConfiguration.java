package cz.muni.fi.pa165.airport_manager.config;

import cz.muni.fi.pa165.airport_manager.dto.AirplaneCreateDTO;
import cz.muni.fi.pa165.airport_manager.entity.Airplane;
import cz.muni.fi.pa165.airport_manager.enums.AirplaneType;
import java.util.ArrayList;
import java.util.Arrays;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
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
        DozerBeanMapper mapper = new DozerBeanMapper();		
		BeanMappingBuilder builder = new BeanMappingBuilder() {

			@Override
			protected void configure() {
				mapping(Airplane.class, AirplaneCreateDTO.class)
						.fields("type", "type", FieldsMappingOptions.customConverter(
								"cz.muni.fi.pa165.airport_manager.config.AirportTypeStringDozenConverter"));
			}
		};
		
		mapper.addMapping(builder);
		
		return mapper;
    }
}

package cz.muni.fi.pa165.airport_manager.config;

import cz.muni.fi.pa165.airport_manager.facade.SampleDataFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Configuration of sample data
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.airport_manager")
public class DataConfiguration {

    @Autowired
    SampleDataFacade sampleDataFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        sampleDataFacade.loadData();
    }
}

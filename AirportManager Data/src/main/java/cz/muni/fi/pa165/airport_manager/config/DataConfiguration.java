package cz.muni.fi.pa165.airport_manager.config;

import cz.muni.fi.pa165.airport_manager.facade.SampleDataFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

/**
 * Configuration of sample data
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackages = "cz.muni.fi.pa165.airport_manager.facade")
public class DataConfiguration {

    public static final String USER_FLIGHT  = "flight";
    public static final String USER_AIRPORT = "airport";
    public static final String USER_ADMIN   = "admin";
    public static final String PASSWD_FLIGHT  = "$2a$10$xftcIph2CW4PqMwbZKXL9uHnKRnQDxAKZi20j04l4fiNY5kEJYtyq";
    public static final String PASSWD_AIRPORT = "$2a$10$vY1mIXR6lOMMB2scxDrozeRt6YAEJMxXgR0PPgpsPikwfWarjj7u2";
    public static final String PASSWD_ADMIN   = "$2a$10$W1j02H.Ha1y8XpFW54yXPeVYZKZJ.AknGRva/S5rBHVOmmozUmzOW";

    public static final String ROLE_FLIGHT  = "ROLE_" + USER_FLIGHT;
    public static final String ROLE_AIRPORT = "ROLE_" + USER_AIRPORT;

    @Autowired
    private SampleDataFacade sampleDataFacade;

    @PostConstruct
    public void dataLoading() throws SQLException {
        sampleDataFacade.loadData();
        sampleDataFacade.loadUsers();
    }
}

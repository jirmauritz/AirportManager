package cz.muni.fi.pa165.airport_manager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration of the application. Uses embedded, in-memory database.
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Configuration
@ComponentScan(basePackages = "cz.muni.fi.pa165")
@EnableTransactionManagement
@EnableJpaRepositories
public class EmbeddedPersistenceContext {

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        return new LocalContainerEntityManagerFactoryBean();
    }

}
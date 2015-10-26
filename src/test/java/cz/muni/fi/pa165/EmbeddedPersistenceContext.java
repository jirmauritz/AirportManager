package cz.muni.fi.pa165;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Configuration
@ComponentScan(basePackages = "cz.muni.fi.pa165")
public class EmbeddedPersistenceContext {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean jpaFactoryBean = new LocalContainerEntityManagerFactoryBean ();
        jpaFactoryBean.setDataSource(db());
        return jpaFactoryBean;
    }

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactoryBean().getObject().createEntityManager();
    }

    @Bean
    public DataSource db(){
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.DERBY).build();
    }

}
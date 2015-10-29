package cz.muni.fi.pa165;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Configuration
@ComponentScan(basePackages = "cz.muni.fi.pa165.dao")
@EnableTransactionManagement
@EnableJpaRepositories
public class EmbeddedPersistenceContext {

    @Bean
    public JpaTransactionManager transactionManager(){
        return  new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean jpaFactoryBean = new LocalContainerEntityManagerFactoryBean ();
        jpaFactoryBean.setDataSource(db());
        return jpaFactoryBean;
    }

    @Bean
    public DataSource db(){
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.DERBY).build();
    }

}
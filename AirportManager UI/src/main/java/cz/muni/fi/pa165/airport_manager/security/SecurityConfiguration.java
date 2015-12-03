package cz.muni.fi.pa165.airport_manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Simple security configuration - defines user roles and credentials
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("flight") .password("123456").roles("FLIGHT");
        auth.inMemoryAuthentication().withUser("airport").password("123456").roles("AIRPORT");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_FLIGHT') or hasRole('ROLE_AIRPORT')")
                .and().formLogin();

    }

}

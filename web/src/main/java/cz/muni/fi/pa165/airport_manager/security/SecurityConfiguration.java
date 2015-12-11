package cz.muni.fi.pa165.airport_manager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Simple security configuration - defines user roles and credentials
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String USER_FLIGHT  = "flight";
    public static final String USER_AIRPORT = "airport";
    public static final String USER_ADMIN   = "admin";
    public static final String PASSWD_FLIGHT  = "$2a$10$xftcIph2CW4PqMwbZKXL9uHnKRnQDxAKZi20j04l4fiNY5kEJYtyq";
    public static final String PASSWD_AIRPORT = "$2a$10$vY1mIXR6lOMMB2scxDrozeRt6YAEJMxXgR0PPgpsPikwfWarjj7u2";
    public static final String PASSWD_ADMIN   = "$2a$10$W1j02H.Ha1y8XpFW54yXPeVYZKZJ.AknGRva/S5rBHVOmmozUmzOW";

    public static final String ROLE_FLIGHT  = "ROLE_" + USER_FLIGHT;
    public static final String ROLE_AIRPORT = "ROLE_" + USER_AIRPORT;
    public static final String ROLE_ADMIN   = "ROLE_" + USER_ADMIN;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        auth.jdbcAuthentication().passwordEncoder(encoder)
                .withUser(USER_FLIGHT) .password(PASSWD_FLIGHT) .roles(USER_FLIGHT);
        auth.jdbcAuthentication().passwordEncoder(encoder)
                .withUser(USER_AIRPORT).password(PASSWD_AIRPORT).roles(USER_AIRPORT);
        auth.jdbcAuthentication().passwordEncoder(encoder)
                .withUser(USER_ADMIN)  .password(PASSWD_ADMIN)  .roles(USER_ADMIN, USER_FLIGHT, USER_AIRPORT);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/**")
                .access("hasRole('" + ROLE_FLIGHT + "') or hasRole('" + ROLE_AIRPORT + "') or hasRole('" + ROLE_ADMIN + "')")
                .and().formLogin()
                .and().logout().logoutSuccessUrl("/login?logout");

    }

}

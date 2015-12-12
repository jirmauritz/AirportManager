package cz.muni.fi.pa165.airport_manager.config;

import cz.muni.fi.pa165.airport_manager.security.SecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.validation.Validator;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * Configuration for UI
 *
 * @author Tomas Valka
 * @author 422718@mail.muni.cz
 */
@Configuration
@Import({DataConfiguration.class, SecurityConfiguration.class})
@ComponentScan(basePackages = "cz.muni.fi.pa165.airport_manager")
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * Maps the main page to a specific view.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login").setViewName("login");
	}

	/**
	 * Provides mapping from view names to JSP pages in WEB-INF/pages directory.
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/**
	 * Provides the resources directory with assets needed on client side.
	 */
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}

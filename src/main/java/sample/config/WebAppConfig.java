package sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MVC configuration.
 *
 * @author Andrii Duplyk
 *
 */
@Configuration
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer

				.favorPathExtension(false)

				.useJaf(false)

				.ignoreAcceptHeader(false)

				.mediaType("html", MediaType.TEXT_HTML)

				.mediaType("json", MediaType.APPLICATION_JSON)

				.defaultContentType(MediaType.APPLICATION_JSON);
	}

}

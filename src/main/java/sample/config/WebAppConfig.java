package sample.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import sample.util.CustomObjectMapper;

/**
 * MVC configuration.
 *
 * @author Andrii Duplyk
 *
 */
@Configuration
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {

	// @Autowired
	// @Qualifier("customObjectMapper")
	// private ObjectMapper objectMapper;

	@Bean
	@Primary
	public ObjectMapper getCustomizedObjectMapper() {
		return new CustomObjectMapper();
	}

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

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		converters.add(jackson2HttpMessageConverter());
	}

	@Bean
	public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {

		MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter();
		jacksonMessageConverter.setObjectMapper(getCustomizedObjectMapper());

		return jacksonMessageConverter;
	}

}

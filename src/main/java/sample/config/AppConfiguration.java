package sample.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;

import sample.util.CustomObjectMapper;

/**
 * Helper config class for embedded Tomcat initialization.
 *
 * @author Andrii Duplyk
 *
 */
@PropertySource(value = "classpath:datasource.properties")
@Configuration
public class AppConfiguration {

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		return factory;
	}

	@Bean
	@Primary
	public ObjectMapper customObjectMapper() {
		CustomObjectMapper customObjectMapper = new CustomObjectMapper();
		return customObjectMapper;
	}

}

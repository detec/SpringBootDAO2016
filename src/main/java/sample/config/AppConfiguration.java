package sample.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Helper config class for embedded Tomcat initialization.
 *
 * @author Andrii Duplyk
 *
 */
@PropertySource(value = { "classpath:datasource.properties", "classpath:application.properties" })
@Configuration
public class AppConfiguration {

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		return factory;
	}

}

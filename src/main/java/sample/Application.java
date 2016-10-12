package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main launch class.
 *
 * @author Andrii Duplyk
 *
 */

@SpringBootApplication(scanBasePackages = { "sample.controller", "sample.config", "sample.util", "sample.dao.jpaimpl",
		"sample.service.impl" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

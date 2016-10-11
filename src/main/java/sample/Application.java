package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Andrii Duplyk
 *
 */

@SpringBootApplication(scanBasePackages = { "sample.controller", "sample.config" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

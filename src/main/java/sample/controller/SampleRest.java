package sample.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Sample controller class to check application is alive.
 *
 * @author Andrii Duplyk
 *
 */

@RestController
public class SampleRest {

	@ApiOperation(value = "testing liveliness of service", nickname = "test")
	@GetMapping("test")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Boolean.class) })

	ResponseEntity<Boolean> testController() {
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}

	@ApiOperation(value = "getting local DateTime", nickname = "getDateTime")
	@GetMapping("now")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = LocalDateTime.class) })
	ResponseEntity<LocalDateTime> getNow() {
		return new ResponseEntity<>(LocalDateTime.now(), HttpStatus.OK);
	}
}

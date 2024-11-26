package com.example.flightapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import com.example.flightapi.utils.TestService;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.flightapi.utils")
public class FlightapiApplication implements CommandLineRunner {
	private final TestService testService;

    // Constructor injection
    public FlightapiApplication(TestService testService) {
        this.testService = testService;
    }

	public static void main(String[] args) {
		SpringApplication.run(FlightapiApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        // Call the createTestEntity method
        testService.createTestEntity("Test Name");
    }
}

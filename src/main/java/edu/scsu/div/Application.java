package edu.scsu.div;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"edu"})
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


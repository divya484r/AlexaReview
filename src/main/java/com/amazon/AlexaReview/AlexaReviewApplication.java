package com.amazon.AlexaReview;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class AlexaReviewApplication {
//
	public static void main(String[] args) {
		//SpringApplication.run(AlexaReviewApplication.class, args);
		ConfigurableApplicationContext ctx = SpringApplication.run(AlexaReviewApplication.class, args);

	}

}

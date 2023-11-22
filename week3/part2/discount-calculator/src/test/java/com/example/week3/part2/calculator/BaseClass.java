package com.example.week3.part2.calculator;

import io.javalin.Javalin;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseClass {

	private final int JAVALIN_USE_RANDOM_PORT = 0;

	DiscountHandler discountHandler = new DiscountHandler(person -> person.setDiscountRate(10d));

	NoBoughtGoodsExceptionHandler exceptionHandler = new NoBoughtGoodsExceptionHandler();

//	int port = JAVALIN_USE_RANDOM_PORT;

	Javalin javalin = new Week2Part3DiscountCalculator(discountHandler, exceptionHandler, JAVALIN_USE_RANDOM_PORT).configureJavalin();

	@BeforeEach
	void setup() {
		javalin.start(JAVALIN_USE_RANDOM_PORT); // Start the Server
		// TODO: Fix me: Tell Rest-Assured which port it should run on
		RestAssured.port = javalin.port();
	}

	@AfterEach
	void clean() {
		javalin.stop();
	} // Stop the Server
}


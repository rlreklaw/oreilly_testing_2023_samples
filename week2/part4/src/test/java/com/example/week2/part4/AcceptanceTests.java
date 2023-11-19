package com.example.week2.part4;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AcceptanceTests {

	@Nested
	class UnitTests {
		// TODO: Bonus
		@Test
		void should_throw_exception_when_not_enough_arguments_were_passed() {

		}
	}

	@Nested
	class IntegrationTests implements RabbitmqTesting{


		int goodsThreshold = 5;
		double goodsDiscountRate = 5D;
		int nameThreshold = 3;
		double nameDiscountRate = 8D;

		@Test
		void should_calculate_maximum_discount() throws Exception {
			String inputMessage = readFile("/json/mrSmith.json");
			String outputMessage = readFile("/json/mrSmithWithDiscountRate.json");
			String inputQueue = "inputQueue";
			String outputQueue = "outputQueue";

			givenAMessageSentToBroker(inputMessage, inputQueue);

			new Week2Part4(goodsThreshold, goodsDiscountRate, nameThreshold, nameDiscountRate, inputQueue, outputQueue, "events", connectionFactory(), connectionFactory(), new ObjectMapper()) {
				@Override
				RabbitMqMessageListener getMessageListener(String inputQueueName, ConnectionFactory inputConnectionFactory, ObjectMapper objectMapper) {
					return new TestRabbitmqMessageListener(inputQueueName, inputConnectionFactory, objectMapper);
				}
			}.calculateDiscount();

			Awaitility.await()
					.untilAsserted(() -> {
						thenMessageWasProperlySentToBroker(outputMessage, outputQueue);
					});
		}

	}
}

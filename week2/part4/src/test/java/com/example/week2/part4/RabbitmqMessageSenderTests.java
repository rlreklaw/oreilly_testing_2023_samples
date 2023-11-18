package com.example.week2.part4;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RabbitmqMessageSenderTests {

	@Nested
	class UnitTests {

		// TODO: Bonus
		@Test
		void should_throw_exception_when_issues_with_connection() throws Exception {

		}
	}

	@Nested
	class IntegrationTests implements RabbitmqTesting {

		String outputMessage;

		String outputQueue = "output";

		RabbitMqMessageSender sender;

		@BeforeEach
		void setup() {
			sender = new RabbitMqMessageSender(outputQueue, connectionFactory(), new ObjectMapper());
			outputMessage = readFile("/json/calculatedEvent.json");
		}

		@Test
		void should_send_message_to_a_queue_in_json_format() {
			sender.sendMessage(new Message(EventType.DISCOUNT_CALCULATED));

			Awaitility.await()
					.untilAsserted(() -> {
						thenMessageWasProperlySentToBroker(outputMessage, outputQueue);
					});
		}
	}

}

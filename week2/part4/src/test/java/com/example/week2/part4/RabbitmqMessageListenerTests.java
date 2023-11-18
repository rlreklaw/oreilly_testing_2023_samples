package com.example.week2.part4;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RabbitmqMessageListenerTests {

	@Nested
	class UnitTests {

		// TODO: Bonus
		@Test
		void should_throw_exception_when_issues_with_connection()
				throws IOException, TimeoutException {

		}
	}

	@Nested
	class IntegrationTests implements RabbitmqTesting {

		String inputMessage;

		String inputQueue = "input";

		TestRabbitmqMessageListener listener;

		@BeforeEach
		void setup() {
			listener = new TestRabbitmqMessageListener(inputQueue, connectionFactory(), new ObjectMapper());
			inputMessage = readFile("/json/mrSmith.json");
		}

		@Test
		void should_receive_a_message_from_broker() throws IOException, TimeoutException {
			givenAMessageSentToBroker(inputMessage, inputQueue);

			listener.pollForMessage(person -> { });

			Awaitility.await()
					.untilAtomic(listener.message,
							Matchers.equalTo(new Person("smith", 100, Occupation.EMPLOYED)));
		}
	}
}

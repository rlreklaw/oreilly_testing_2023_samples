package com.example.week2.part3;

import java.util.Collections;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes = DiscountControllerMockMvcTests.MyTestConfiguration.class)
class DiscountControllerMockMvcTests {

	@Autowired MockMvc mockMvc;

	@Autowired ObjectMapper objectMapper;

	// Successful scenario - serialization + deserialization
	@Test
	void should_calculate_discount_rate_for_person() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/discount")
						.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(
						new Person("foo", 100, Occupation.UNEMPLOYED))))
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.personName", equalTo("foo")))
				.andExpect(jsonPath("$.discountRate", equalTo(10.0D)));

	}

	// Error scenario - Validation handling
	@Test
	void should_fail_for_empty_name() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/discount")
						.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(
						new Person("", 100, Occupation.UNEMPLOYED))))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}

	// Error scenario - custom exception handling
	@Test
	void should_fail_for_no_goods() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/discount")
						.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(
						new Person("foo", 0, Occupation.UNEMPLOYED))))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
				.andExpect(jsonPath("$.person.name", equalTo("foo")))
				.andExpect(jsonPath("$.additionalMessage",
						equalTo("We can't apply discounts to people who didn't buy any goods")));
	}

	@TestConfiguration(proxyBeanMethods = false)
	static class MyTestConfiguration {
		@Bean
		DiscountCalculator discountCalculator() {
			return new DiscountCalculator(Collections.emptyList()) {
				@Override
				public void calculateTotalDiscountRate(Person person) {
					person.setDiscountRate(10);
				}
			};
		}
	}
}

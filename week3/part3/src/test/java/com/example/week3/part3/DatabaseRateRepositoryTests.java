package com.example.week3.part3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class DatabaseRateRepositoryTests {

	@ParameterizedTest(name = "[{index}] For occupation<{0}>, discount is <{1}>")
	@CsvSource(value = {
			"EMPLOYED, 10",
			"UNEMPLOYED, 0",
			"null, 0"
	}, nullValues = "null")
	void should_return_discount_for_occupation(Occupation occupation, double discount) {
		assertThat(new DatabaseRateRepository().getDiscountRate(occupation))
				. isEqualTo(discount);
	}
}

package com.example.week3.part3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.BDDAssertions.then;

class OccupationDiscountApplierTests {

	@ParameterizedTest(name = "[{index}] Occupation <{0}> should have discount <{1}>")
	@CsvSource(value = {
			"EMPLOYED, 10",
			"UNEMPLOYED, 0",
			"null, 0"
	}, nullValues = "null")
	void should_return_a_discount_rate_from_a_repository(Occupation occupation, double discount) {
		OccupationDiscountApplier applier = new OccupationDiscountApplier(occ -> discount);

		double discountRate = applier.getDiscountRate(person(occupation));

		then(discountRate)
				.as("Discount rate should be taken directly from the repository")
				.isEqualTo(discount);
	}

	private static Person person(Occupation occupation) {
		return new Person("name", 100, occupation);
	}
}

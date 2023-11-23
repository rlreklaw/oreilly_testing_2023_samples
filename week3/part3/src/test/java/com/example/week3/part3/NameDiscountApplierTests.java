package com.example.week3.part3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.BDDAssertions.then;


class NameDiscountApplierTests {

	@ParameterizedTest(name = "[{index}] Name <{0}> should get discount <{1}>")
	@CsvSource(value = {
			"sa, 0",
			"sam, 8",
			"samuel, 8",
			"null, 0"
	}, nullValues = "null")
	void should_return_discount_for_person_name(String name, double discount) {
		NameDiscountApplier applier = new NameDiscountApplier();

		double discountRate = applier.getDiscountRate(person(name));

		then(discountRate).isEqualTo(discount);
	}

	private static Person person(String name) {

		return new Person(name, 0, Occupation.UNEMPLOYED);
	}

}

package com.example.week1.part2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.example.week1.part2.assertion.DiscountAssert.assertThat;

public class DiscountCalculatorTests {


	@Test
	void should_return_no_discount_for_no_appliers(){

		DiscountCalculator dc = new DiscountCalculator(Collections.emptyList());

		Discount discount = dc.calculateTotalDiscountRate(person());

		assertThat(discount).isNotSet();
	}

	@Test
	void should_return_discount_rate_when_appliers_are_present() {
		final double FIXED_DISCOUNT_RATE1 = 6D;
		final double FIXED_DISCOUNT_RATE2 = 17D;

		DiscountCalculator dc = new DiscountCalculator(Arrays.asList(person -> FIXED_DISCOUNT_RATE1,
																		person -> FIXED_DISCOUNT_RATE2));

		Discount discount = dc.calculateTotalDiscountRate(person());

		assertThat(discount).isEqualTo(FIXED_DISCOUNT_RATE1 + FIXED_DISCOUNT_RATE2);
	}

	private Person person() {
		return new Person("Homer", 42, Occupation.EMPLOYED);
	}
}

package com.example.week1.part2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorTests {


	@Test
	void should_return_no_discount_for_no_appliers(){
		final double NO_DISCOUNT = 0D;

		DiscountCalculator dc = new DiscountCalculator(Collections.EMPTY_LIST);

		Discount discount = dc.calculateTotalDiscountRate(person());

		assertEquals(NO_DISCOUNT, discount.getRate(), 0.01D);
	}

	@Test
	void should_return_discount_rate_when_appliers_are_present() {
		final double FIXED_DISCOUNT_RATE1 = 6D;
		final double FIXED_DISCOUNT_RATE2 = 17D;

		DiscountCalculator dc = new DiscountCalculator(Arrays.asList(person -> FIXED_DISCOUNT_RATE1,
																		person -> FIXED_DISCOUNT_RATE2));

		Discount discount = dc.calculateTotalDiscountRate(person());

		assertEquals(FIXED_DISCOUNT_RATE1 + FIXED_DISCOUNT_RATE2, discount.getRate(), 0.01D);
	}

	private Person person() {
		return new Person("Homer", 42, Occupation.EMPLOYED);
	}
}

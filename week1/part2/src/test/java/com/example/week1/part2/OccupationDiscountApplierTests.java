package com.example.week1.part2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OccupationDiscountApplierTests {

	@Test
	public void should_return_discount_rate_from_repository() {
		final double FIXED_RATE = 42D;

		OccupationDiscountApplier a = new OccupationDiscountApplier(person -> FIXED_RATE);

		Person p = new Person("name", 100, Occupation.UNEMPLOYED);

		double discountRate = a.getDiscountRate(p);

		Assertions.assertEquals(discountRate, FIXED_RATE, 0D);
	}
}

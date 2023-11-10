package com.example.week1.part2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OccupationDiscountApplierTests {

	// WARNING: This test is slow it connects to a database
	@Test
	public void testGetDiscountRate() {
		OccupationDiscountApplier a = new OccupationDiscountApplier();

		Person p = new Person("name", 100, Occupation.UNEMPLOYED);

		double discountRate = a.getDiscountRate(p);

		Assertions.assertEquals(discountRate, 0D, 0D);
	}
}

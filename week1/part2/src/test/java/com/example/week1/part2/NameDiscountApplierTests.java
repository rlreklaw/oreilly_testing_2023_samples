package com.example.week1.part2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class NameDiscountApplierTests {

	private final int MIN_VALID_NAME_LEN = 3;
	private final double NO_DISCOUNT_VALUE = 0D;
	private final double NAME_DISCOUNT_VALUE = 8D;

	private static NameDiscountApplier discountApplier;

	@BeforeAll
	static void initAll() {
		discountApplier = new NameDiscountApplier();
	}

	@Test
	void should_get_discount_for_person_name_long_enough() {
		Person validNamePerson = new Person("Diana", 42, Occupation.EMPLOYED);

		double discount = discountApplier.getDiscountRate(validNamePerson);

		Assertions.assertEquals(NAME_DISCOUNT_VALUE, discount, 0.01D);
	}

	@Test
	void should_get_no_discount_for_person_name_too_short() {
		Person validNamePerson = new Person("Di", 42, Occupation.EMPLOYED);

		double discount = discountApplier.getDiscountRate(validNamePerson);

		Assertions.assertEquals(NO_DISCOUNT_VALUE, discount, 0.01D);
	}
}

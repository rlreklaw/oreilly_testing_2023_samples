package com.example.week1.part3;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.example.week1.part3.assertion.PersonAssert.assertThat;

class DiscountCalculatorTests {

	@Test
	public void should_not_have_discount_when_no_appliers() {
		List<DiscountApplier> appliers = Collections.emptyList();
		DiscountCalculator calculator = new DiscountCalculator(appliers);
		Person person = person();

		calculator.calculateTotalDiscountRate(person);

		assertThat(person).hasNoDiscount();
	}

	@Test
	public void should_calculate_total_discount_when_appliers_present() {
		final double DISCOUNT_RATE1 = 3D;
		final double DISCOUNT_RATE2 = 7D;

		List<DiscountApplier> appliers = Arrays.asList(
				person -> person.setDiscountRate(person.getDiscountRate() + DISCOUNT_RATE1),
				person -> person.setDiscountRate(person.getDiscountRate() + DISCOUNT_RATE2)
		);
		DiscountCalculator calculator = new DiscountCalculator(appliers);
		Person person = person();

		calculator.calculateTotalDiscountRate(person);

		assertThat(person).hasDiscountRateEqualTo(DISCOUNT_RATE1 + DISCOUNT_RATE2);
	}

	private Person person() {
		return new Person("Test", 42, Occupation.EMPLOYED);
	}
}

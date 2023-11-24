package com.example.week3.part4;

import net.jqwik.api.*;
import org.assertj.core.api.BDDAssertions;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import static com.example.week3.part4.assertion.DiscountAssert.then;

class DiscountCalculatorTests {

	@Example
	void should_return_no_discount_when_person_null() {
		DiscountCalculator discountCalculator = new DiscountCalculator(Collections.singletonList(person -> {
			throw new AssertionError("Shouldn't be called");
		}));

		Discount discount = discountCalculator.calculateTotalDiscountRate(null);

		then(discount).isNotSet();
	}

	@Property
	void should_calculate_total_discount_when_appliers_present(
			@ForAll("validPeople") Person person
	) {
		double fixedDiscountRate1 = 5D;
		double fixedDiscountRate2 = 7D;
		DiscountCalculator discountCalculator = new DiscountCalculator(Arrays.asList(aPerson -> fixedDiscountRate1, aPerson -> fixedDiscountRate2));

		Discount discount = discountCalculator.calculateTotalDiscountRate(person);

		then(discount).isEqualTo(fixedDiscountRate1 + fixedDiscountRate2);
		String discountName = discount.getDiscountName();
		BDDAssertions.then(discountName).startsWith(DiscountCalculator.DISCOUNT_PREFIX);
		BigInteger number = new BigInteger(discountName.substring(DiscountCalculator.DISCOUNT_PREFIX.length()));
		BDDAssertions.then(number).isPositive();
	}

	@Provide
	Arbitrary<Person> validPeople() {
		Arbitrary<Long> timestamps = Arbitraries.longs().greaterOrEqual(1);
		Arbitrary<String> names = Arbitraries.strings().ofMinLength(2);
		Arbitrary<Integer> itemCounts = Arbitraries.integers().greaterOrEqual(0);
		Arbitrary<Occupation> occupations = Arbitraries.of(Occupation.class);
		return Combinators.combine(timestamps, names, itemCounts, occupations).as(Person::new);
	}
}

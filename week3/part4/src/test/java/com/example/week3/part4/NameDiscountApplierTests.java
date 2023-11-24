package com.example.week3.part4;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.StringLength;

import static org.assertj.core.api.BDDAssertions.then;


class NameDiscountApplierTests {

	@Property
	void should_return_discount_for_long_enough_name(
			@ForAll @StringLength(min=NameDiscountApplier.LOWER_THRESHOLD + 1,
			max=NameDiscountApplier.UPPER_THRESHOLD - 1) String name
	) {
		then(new NameDiscountApplier().getDiscountRate(new Person(name, 0, Occupation.UNEMPLOYED)))
				.isEqualTo(NameDiscountApplier.DISCOUNT_RATE);

	}

	@Property
	void should_not_return_discount_for_name_too_short(
			@ForAll @StringLength(min=2, max=NameDiscountApplier.LOWER_THRESHOLD) String name
	) {
		then(new NameDiscountApplier().getDiscountRate(new Person(name, 0, Occupation.UNEMPLOYED)))
				.isZero();

	}

	@Property
	void should_not_return_discount_for_name_too_long(
			@ForAll @StringLength(min=NameDiscountApplier.UPPER_THRESHOLD) String name
	) {
		then(new NameDiscountApplier().getDiscountRate(new Person(name, 0, Occupation.UNEMPLOYED)))
				.isZero();

	}


}

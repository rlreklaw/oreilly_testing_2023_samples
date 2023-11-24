package com.example.week3.part4;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

import static org.assertj.core.api.BDDAssertions.then;


class NoOfBoughtGoodsDiscountApplierTests {

	@Property
	void return_discount_for_enough_items_bought(
			@ForAll @IntRange(min=NoOfBoughtGoodsDiscountApplier.THRESHOLD + 1) int itemCount
	) {
		then(new NoOfBoughtGoodsDiscountApplier().getDiscountRate(new Person("foo", itemCount, Occupation.UNEMPLOYED)))
				.isEqualTo(NoOfBoughtGoodsDiscountApplier.DISCOUNT_RATE);

	}

	@Property
	void return_no_discount_for_too_few_items_bought(
			@ForAll @IntRange(max=NoOfBoughtGoodsDiscountApplier.THRESHOLD) int itemCount
	) {
		then(new NoOfBoughtGoodsDiscountApplier().getDiscountRate(new Person("foo", itemCount, Occupation.UNEMPLOYED)))
				.isZero();

	}
}

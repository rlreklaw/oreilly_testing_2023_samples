package com.example.week3.part3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.BDDAssertions.then;


class NoOfBoughtGoodsDiscountApplierTests {

	@ParameterizedTest(name = "[{index}] Number of items bought <{0}> should have discount<{1}>")
	@CsvSource(value = {
			"4, 0",
			"5, 5",
			"6, 5",
			"0, 0",
			"null, 0"
	}, nullValues = "null")
	void should_should_return_discount_for_number_of_goods_bought(Integer itemCount, double discount) {
		NoOfBoughtGoodsDiscountApplier applier = new NoOfBoughtGoodsDiscountApplier();

		double discountRate = applier.getDiscountRate(person(itemCount));

		then(discountRate).isEqualTo(discount);

	}

/*
	@Test
	void should_return_a_discount_rate_when_person_has_no_of_goods_above_threshold() {
		NoOfBoughtGoodsDiscountApplier applier = new NoOfBoughtGoodsDiscountApplier();

		double discountRate = applier.getDiscountRate(personWithNumberOfGoodsAboveThreshold());

		then(discountRate).isEqualTo(NoOfBoughtGoodsDiscountApplier.DISCOUNT_RATE);
	}

	@Test
	void should_return_no_discount_rate_when_person_has_no_of_goods_below_threshold() {
		NoOfBoughtGoodsDiscountApplier applier = new NoOfBoughtGoodsDiscountApplier();

		double discountRate = applier.getDiscountRate(personWithNumberOfGoodsBelowThreshold());

		then(discountRate).isEqualTo(0D);
	}

	@Test
	void should_return_no_discount_rate_when_person_has_null_no_of_goods() {
		NoOfBoughtGoodsDiscountApplier applier = new NoOfBoughtGoodsDiscountApplier();

		double discountRate = applier.getDiscountRate(person(null));

		then(discountRate).isEqualTo(0D);
	}

	private static Person personWithNumberOfGoodsAboveThreshold() {
		return new Person("name", NoOfBoughtGoodsDiscountApplier.THRESHOLD + 1, Occupation.UNEMPLOYED);
	}

	private static Person personWithNumberOfGoodsBelowThreshold() {
		return new Person("name", NoOfBoughtGoodsDiscountApplier.THRESHOLD - 1, Occupation.UNEMPLOYED);
	}
*/

	private static Person person(Integer itemCount) {
		return new Person("name", itemCount, Occupation.UNEMPLOYED);
	}

}

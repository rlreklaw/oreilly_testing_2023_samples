package com.example.week1.part2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class NoOfBoughtGoodsDiscountApplierTests {

	private final int MIN_ITEM_COUNT_FOR_DISCOUNT = 5;

	private static NoOfBoughtGoodsDiscountApplier discountApplier;

	@BeforeAll
	static void initAll() {
		discountApplier = new NoOfBoughtGoodsDiscountApplier();
	}


	@Test
	void should_get_no_discount_for_too_few_items() {
		Person tooFewItemsPerson = new Person("Homer",  MIN_ITEM_COUNT_FOR_DISCOUNT - 1, Occupation.EMPLOYED);

		double discount = discountApplier.getDiscountRate(tooFewItemsPerson);

		double NO_DISCOUNT = 0D;
		Assertions.assertEquals(NO_DISCOUNT, discount, 0.01D);
	}

	@Test
	void should_get_discount_for_enough_items() {
		Person enoughItemsPerson = new Person("Homer",  MIN_ITEM_COUNT_FOR_DISCOUNT, Occupation.EMPLOYED);

		double discount = discountApplier.getDiscountRate(enoughItemsPerson);

		double ITEM_COUNT_DISCOUNT = 5D;
		Assertions.assertEquals(ITEM_COUNT_DISCOUNT, discount, 0.01D);
	}

}

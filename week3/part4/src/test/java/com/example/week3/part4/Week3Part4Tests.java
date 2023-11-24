package com.example.week3.part4;

import net.jqwik.api.*;

import static com.example.week3.part4.assertion.DiscountAssert.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

class Week3Part4Tests {
	@Example
	void should_throw_exception_when_not_enough_arguments_were_passed() {
		thenThrownBy(() -> Week3Part4.main(new String[] {"foo", "bar"}))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Wrong number of arguments");
	}

	@Property
	void should_calculate_maximum_discount(@ForAll("maxDiscountPeople") Person person) {

		Discount discount = new Week3Part4().calculateDiscount(person);

		then(discount)
				.as("Maximum discount is <8> for name, <5> for no of goods, <10> for occupation; total of <23>")
				.isEqualTo(23);
	}

	@Provide
	Arbitrary<Person> maxDiscountPeople() {
		Arbitrary<Long> timestamps = Arbitraries.longs().greaterOrEqual(1);
		Arbitrary<String> names = Arbitraries.strings()
				.ofMinLength(NameDiscountApplier.LOWER_THRESHOLD + 1)
				.ofMaxLength(NameDiscountApplier.UPPER_THRESHOLD - 1);
		Arbitrary<Integer> itemCounts = Arbitraries.integers()
				.greaterOrEqual(NoOfBoughtGoodsDiscountApplier.THRESHOLD + 1);
		Arbitrary<Occupation> occupations = Arbitraries.of(Occupation.class);
		return Combinators.combine(timestamps, names, itemCounts, occupations).as(Person::new);
	}
}


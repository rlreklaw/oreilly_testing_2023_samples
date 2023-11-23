package com.example.week3.part3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class Week3Part3Test {
	@Test
	void should_throw_exception_when_too_few_arguments_specified() {
		assertThatThrownBy(() -> Week3Part3.main(new String [] {"foo", "bar"}))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("There must be exactly 3 arguments");
	}

	@Test
	void should_calculate_maximum_discount() {
		Person person = new Person("long enough name", 42, Occupation.EMPLOYED);
		Discount discount = new Week3Part3().calculateDiscount(person);

		assertThat(discount.getRate())
				.as("Maximum discount is <8> for name, <5> for number of items, <10> for occupation for a total of 23")
				.isEqualTo(23d);
	}
}

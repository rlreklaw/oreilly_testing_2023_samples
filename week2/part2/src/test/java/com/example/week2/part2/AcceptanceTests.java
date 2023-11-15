package com.example.week2.part2;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

public class AcceptanceTests {

	@Nested
	class UnitTests {

		@Test
		void should_throw_exception_when_not_enough_arguments_were_passed() {
			assertThatThrownBy(() -> Week2Part2.main(new String[] {"foo", "bar"}))
					.isInstanceOf(IllegalArgumentException.class)
					.hasMessageContaining("Wrong number of arguments");
		}

	}

	@Nested
	@SpringJUnitConfig(DiscountConfiguration.class)
	@TestPropertySource(properties = {"name.threshold=3", "name.discount=10.0", "items.threshold=5",
			"items.discount=5.0"})
	class IntegrationTests {

		@Test
		void should_calculate_maximum_discount(@Autowired DiscountCalculator discountCalculator,
											   @Value("${name.discount}") double nameDiscount,
											   @Value("${items.discount}") double itemsDiscount) {
			final int ITEM_COUNT_ABOVE_THRESHOLD = 42;
			final Occupation MAX_DISCOUNT_OCCUPATION = Occupation.EMPLOYED;
			final double occupationDiscount = 10;

			Person person = new Person("name longer than discount threshold",
					ITEM_COUNT_ABOVE_THRESHOLD, MAX_DISCOUNT_OCCUPATION);

			Discount discount = discountCalculator.calculateTotalDiscountRate(person);
			then(discount.getRate())
					.as("Maximum discount is <%s>: name discount <%s>, item count discount <%s>, occupation discount <%s>",
							nameDiscount + itemsDiscount + occupationDiscount,
							nameDiscount, itemsDiscount, occupationDiscount)
					.isEqualTo(nameDiscount + itemsDiscount + occupationDiscount);
		}
	}
}

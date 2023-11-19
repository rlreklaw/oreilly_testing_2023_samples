package com.example.week2.part5;

import java.sql.SQLException;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AcceptanceTests {

	@Nested
	class UnitTests {
		@Test
		void should_throw_exception_when_not_enough_arguments_were_passed() {
			// TODO: Check how <Week2Part5.main> works with not enough arguments
			assertThatThrownBy(() -> Week2Part5.main(new String[] {"arg1", "arg2", "arg3", "arg4", "arg5"}))
					.isInstanceOf(IllegalArgumentException.class)
					.hasMessageContaining("Wrong number of arguments ");
		}
	}

	@Nested
	class IntegrationTests implements DbDiscountTesting {

		@Test
		void should_calculate_maximum_discount() throws SQLException {
			givenDiscountRateWasInDatabase("for employed", Occupation.EMPLOYED, 10D );

			Discount discount = new Week2Part5(DB_USER, DB_PASSWORD, jdbcUrl()).calculateDiscount(
					new Person("foo", 42, Occupation.EMPLOYED)
			);

			assertThat(discount.getRate())
					.describedAs("max discount is 8 for name, 5 for goods, 10 for occupation - total 23")
					.isEqualTo(23D);
		}

	}

}

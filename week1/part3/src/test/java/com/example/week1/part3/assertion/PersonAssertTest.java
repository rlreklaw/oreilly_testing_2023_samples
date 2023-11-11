package com.example.week1.part3.assertion;

import com.example.week1.part3.Occupation;
import com.example.week1.part3.Person;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PersonAssertTest {

	@Test
	public void should_not_throw_exception_when_no_discount() {
		PersonAssert personAssert = new PersonAssert(person("Homer", 42, Occupation.UNEMPLOYED));
		assertThatNoException().isThrownBy(personAssert::hasNoDiscount);
	}

	@Test
	public void should_throw_exception_when_discount_present() {
		Person person = person("Marge", 13, Occupation.EMPLOYED);
		person.setDiscountRate(5D);
		PersonAssert personAssert = new PersonAssert(person);
		assertThatThrownBy(personAssert::hasNoDiscount)
				.isInstanceOf(AssertionError.class)
				.hasMessage("Expected person to have no discount, but discount rate was <%s>",
						person.getDiscountRate());
	}

	@Test
	public void should_not_throw_exception_when_discount_equal() {
		final double DISCOUNT_RATE = 7D;

		Person person = person("Fred", 11, Occupation.EMPLOYED);
		person.setDiscountRate(DISCOUNT_RATE);
		PersonAssert personAssert = new PersonAssert(person);

		assertThatNoException().isThrownBy(() -> personAssert.hasDiscountRateEqualTo(DISCOUNT_RATE));
	}

	@Test
	public void should_throw_exception_when_discount_not_equal() {
		final double ACTUAL_DISCOUNT_RATE = 7D;
		final double EXPECTED_DISCOUNT_RATE = 11D;

		Person person = person("Wilma", 3, Occupation.UNEMPLOYED);
		person.setDiscountRate(ACTUAL_DISCOUNT_RATE);
		PersonAssert personAssert = new PersonAssert(person);

		assertThatThrownBy(() -> personAssert.hasDiscountRateEqualTo(EXPECTED_DISCOUNT_RATE))
				.isInstanceOf(AssertionError.class)
				.hasMessage("Expected discount rate to be <%s> but was <%s>",
						EXPECTED_DISCOUNT_RATE, ACTUAL_DISCOUNT_RATE);
	}

	private Person person(String name, int itemCount, Occupation occupation) {
		return new Person(name, itemCount, occupation);
	}
}

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
	public void hasDiscountRateEqualTo() {
	}

	private Person person(String name, int itemCount, Occupation occupation) {
		return new Person(name, itemCount, occupation);
	}
}

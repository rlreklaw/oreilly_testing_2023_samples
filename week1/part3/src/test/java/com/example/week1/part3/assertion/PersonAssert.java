package com.example.week1.part3.assertion;

import com.example.week1.part3.Person;
import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class PersonAssert extends AbstractAssert<PersonAssert,Person> {
	public PersonAssert(Person person) {
		super(person, PersonAssert.class);
	}

	public static PersonAssert assertThat(Person person) {
		return new PersonAssert(person);
	}

	public PersonAssert hasNoDiscount() {
		isNotNull();

		if (! Objects.equals(0D, actual.getDiscountRate())) {
			failWithMessage("Expected person to have no discount, but discount rate was <%s>",
					actual.getDiscountRate());
		}

		return this;
	}

	public PersonAssert hasDiscountRateEqualTo(double rate) {
		isNotNull();

		if (! Objects.equals(rate, actual.getDiscountRate())) {
			failWithMessage("Expected discount rate to be <%s> but was <%s>",
					rate, actual.getDiscountRate());
		}

		return this;
	}
}

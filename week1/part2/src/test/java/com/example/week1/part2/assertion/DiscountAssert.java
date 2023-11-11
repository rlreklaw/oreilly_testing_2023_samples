package com.example.week1.part2.assertion;

import com.example.week1.part2.Discount;
import org.assertj.core.api.AbstractAssert;
import java.util.Objects;

public class DiscountAssert extends AbstractAssert<DiscountAssert, Discount> {
	public DiscountAssert(Discount actual) {
		super(actual, DiscountAssert.class);
	}

	public static DiscountAssert assertThat(Discount actual) {
		return new DiscountAssert(actual);
	}

	public DiscountAssert isNotSet() {
		isNotNull();

		if (! Objects.equals(0D, actual.getRate())) {
			failWithMessage("Expected discount to be <0D> but was <%d>", actual.getRate());
		}

		return this;
	}

	public DiscountAssert isEqualTo(double rate) {
		isNotNull();

		if (! Objects.equals(rate, actual.getRate())) {
			failWithMessage("Expected discount to be <%d> but was <%d>", rate, actual.getRate());
		}

		return this;
	}
}

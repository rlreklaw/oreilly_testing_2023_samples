package com.example.week1.part2;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiscountCalculatorTests {

	DiscountCalculator d = new DiscountCalculator();

	List<DiscountApplier> a = new ArrayList<>();

	@BeforeEach
	public void setup() {
		 ReflectionUtils.injectDependency(DiscountCalculator.class, "discountAppliers", d, a);
	}

	@Test
	public void testCalculateTotalDiscountRate() {
		Person p = new Person("test", 1, Occupation.UNEMPLOYED);
		Discount dis = d.calculateTotalDiscountRate(p);
		Assertions.assertEquals(0D, dis.getRate(), 0D);
	}
}

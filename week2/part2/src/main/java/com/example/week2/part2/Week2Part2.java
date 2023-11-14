package com.example.week2.part2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Week2Part2 {

	// name noOfBoughtGoods Occupation
	// foo 15 EMPLOYED
	// bar 5 UNEMPLOYED
	public static void main(String[] args) {
		if (args.length != 3) {
			throw new IllegalArgumentException("Wrong number of arguments " + Arrays.toString(args) + ". There must be exactly 3 arguments");
		}
		Person person = new Person(args[0], Integer.parseInt(args[1]), Occupation.valueOf(args[2]));
		new Week2Part2().calculateDiscount(person);
		System.exit(0);
	}

	void calculateDiscount(Person person) {
		ApplicationContext context = new AnnotationConfigApplicationContext(DiscountConfiguration.class);
		DiscountCalculator discountCalculator = context.getBean(DiscountCalculator.class);

//		List<DiscountApplier> appliers = Arrays.asList(new OccupationDiscountApplier(new FixedRateRepository()), new ItemsBoughtCountDiscountApplier(4, 8), new NameDiscountApplier(6, 5));
//		DiscountCalculator discountCalculator = new DiscountCalculator(appliers);
//		Discount totalDiscount = discountCalculator.calculateTotalDiscountRate(person);

		Discount totalDiscount = discountCalculator.calculateTotalDiscountRate(person);
		System.out.println("Total discount rate for person [" + person + "] is equal to [" + totalDiscount.getRate() + "]");
	}
}

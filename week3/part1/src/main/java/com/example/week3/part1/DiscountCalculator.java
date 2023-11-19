package com.example.week3.part1;

import java.util.List;
import java.util.logging.Logger;

import com.example.week3.part1.applier.DiscountApplier;
import com.example.week3.part1.model.Discount;
import com.example.week3.part1.model.Person;

public class DiscountCalculator {
	final Logger LOG = Logger.getLogger(DiscountCalculator.class.getName());

	private final List<DiscountApplier> discountAppliers;

	// Constructors FTW!
	public DiscountCalculator(List<DiscountApplier> discountAppliers) {
		this.discountAppliers = discountAppliers;
	}

	public Discount calculateTotalDiscountRate(Person person) {
		LOG.fine("Calculating discount rate for person [" + person + "]");
		return new Discount(this.discountAppliers
				.stream()
				.map(discountApplier -> discountApplier.getDiscountRate(person))
				.reduce(0D, Double::sum));
	}

}

package com.example.week3.part1.applier;

import com.example.week3.part1.model.Person;

import java.util.logging.Logger;

class NameDiscountApplier implements DiscountApplier {
	final Logger LOG = Logger.getLogger(NameDiscountApplier.class.getName());

	static final int THRESHOLD = 3;

	static final double DISCOUNT_RATE = 8D;

	@Override
	public double getDiscountRate(Person person) {
		LOG.fine("Calculating name discount");
		if (person.getName().length() < THRESHOLD) { // most likely a prank!
			return 0D;
		}
		return DISCOUNT_RATE;
	}
}

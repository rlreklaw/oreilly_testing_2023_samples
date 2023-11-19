package com.example.week3.part1.applier;

import com.example.week3.part1.model.Person;

import java.util.logging.Logger;

public class NoOfBoughtGoodsDiscountApplierImpl implements DiscountApplier {
	Logger LOG = Logger.getLogger(NoOfBoughtGoodsDiscountApplierImpl.class.getName());

	// This should be configurable
	static final int THRESHOLD = 5;

	static final double DISCOUNT_RATE = 5D;

	@Override
	public double getDiscountRate(Person person) {
		LOG.fine("Calculating number of goods discount");
		if (person.getNumberOfBoughtGoods() < THRESHOLD) {
			return 0D;
		}
		return DISCOUNT_RATE;
	}
}

package com.example.week3.part1.repository;

import com.example.week3.part1.applier.DiscountApplier;
import com.example.week3.part1.model.Occupation;

import java.util.logging.Logger;

public class DbRateRepo implements RateRepository {
	final Logger LOG = Logger.getLogger(DbRateRepo.class.getName());

	// TODO: Maybe will be used later
	DiscountApplier discountApplier;

	@Override
	public double getDiscountRate(Occupation occupation) {
		LOG.fine("Got occupation [" + occupation + "]");
		return OccupationUtils.getRateForOccupation(occupation);
	}
}

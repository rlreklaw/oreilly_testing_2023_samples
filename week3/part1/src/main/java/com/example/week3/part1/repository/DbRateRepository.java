package com.example.week3.part1.repository;

import com.example.week3.part1.model.Occupation;

import java.util.logging.Logger;

class DbRateRepository implements RateRepository {
	final Logger LOG = Logger.getLogger(DbRateRepository.class.getName());

	@Override
	public double getDiscountRate(Occupation occupation) {
		LOG.fine("Got occupation [" + occupation + "]");
		return OccupationUtils.getRateForOccupation(occupation);
	}
}

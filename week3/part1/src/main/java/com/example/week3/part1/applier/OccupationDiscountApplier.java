package com.example.week3.part1.applier;

import com.example.week3.part1.model.Person;
import com.example.week3.part1.repository.RateRepository;

import java.util.logging.Logger;

class OccupationDiscountApplier implements DiscountApplier {
	Logger LOG = Logger.getLogger(OccupationDiscountApplier.class.getName());

	private final RateRepository rateRepository;

	public OccupationDiscountApplier(RateRepository rateRepository) {
		this.rateRepository = rateRepository;
	}

	@Override
	public double getDiscountRate(Person person) {
		LOG.fine("Calculating occupation discount");
		return rateRepository.getDiscountRate(person.getOccupation());
	}
}

package com.example.week3.part1.applier;

import com.example.week3.part1.model.Person;
import com.example.week3.part1.repository.RateRepository;

import java.util.logging.Logger;

public class OccupationDiscountApplication implements DiscountApplier {
	Logger LOG = Logger.getLogger(OccupationDiscountApplication.class.getName());

	private final RateRepository rateRepository;

	public OccupationDiscountApplication(RateRepository rateRepository) {
		this.rateRepository = rateRepository;
	}

	@Override
	public double getDiscountRate(Person person) {
		LOG.fine("Calculating occupation discount");
		return rateRepository.getDiscountRate(person.getOccupation());
	}
}

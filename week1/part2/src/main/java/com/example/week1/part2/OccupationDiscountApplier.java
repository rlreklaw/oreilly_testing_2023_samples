package com.example.week1.part2;

public class OccupationDiscountApplier implements DiscountApplier {
	OccupationRateRepository rateRepository;

	public OccupationDiscountApplier(OccupationRateRepository repository) {
		this.rateRepository = repository;
	}

	@Override
	public double getDiscountRate(Person person) {
		System.out.println("Calculating occupation discount");
		return rateRepository.getRate(person.getOccupation());
	}
}

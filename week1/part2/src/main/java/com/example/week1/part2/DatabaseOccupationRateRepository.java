package com.example.week1.part2;

public class DatabaseOccupationRateRepository implements OccupationRateRepository {

	@Override
	public double getRate(Occupation occupation) {
		return OccupationUtils.getRateForOccupation(occupation);
	}
}

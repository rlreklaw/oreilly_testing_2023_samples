package com.example.week3.part1.repository;

import com.example.week3.part1.model.Occupation;

import java.util.logging.Logger;

public class OccupationUtils {
	final static Logger LOG = Logger.getLogger(OccupationUtils.class.getName());
	private OccupationUtils() {};

	public static double getRateForOccupation(Occupation occupation) {
		try {
			LOG.fine("Connecting to the database... please wait");
			// Simulating database connection
			Thread.sleep(10_000);
			LOG.fine("Database query completed");
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		if (occupation == Occupation.UNEMPLOYED) {
			return 0D;
		}
		return 10D;
	}

}

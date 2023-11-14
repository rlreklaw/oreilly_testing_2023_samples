package com.example.week2.part2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration(proxyBeanMethods = false)
public class DiscountConfiguration {

	@Bean
	DiscountCalculator discountCalculator(List<DiscountApplier> appliers){
		return new DiscountCalculator(appliers);
	}

	@Bean
	NameDiscountApplier nameDiscountApplier(@Value("${name.threshold") int threshold,
											@Value("${name.discount}") double discountRate) {
		return new NameDiscountApplier(threshold, discountRate);
	}

	@Bean
	ItemsBoughtCountDiscountApplier itemsBoughtCountDiscountApplier(@Value("${items.threshold") int threshold,
											@Value("${items.discount}") double discountRate) {
		return new ItemsBoughtCountDiscountApplier(threshold, discountRate);
	}

	@Bean
	FixedRateRepository fixedRateRepository() {
		return new FixedRateRepository();
	}

	@Bean
	OccupationDiscountApplier occupationDiscountApplier(RateRepository rateRepository) {
		return new OccupationDiscountApplier(rateRepository);
	}
}

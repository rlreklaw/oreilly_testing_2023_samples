package com.example.week2.part2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration(proxyBeanMethods = false)
public class DiscountConfiguration {

	@Bean
	DiscountCalculator discountCalculator(List<DiscountApplier> appliers){
		return new DiscountCalculator(appliers);
	}

	@Bean
	NameDiscountApplier nameDiscountApplier(@Value("${name.threshold:6}") int threshold,
											@Value("${name.discount:5}") double discountRate) {
		return new NameDiscountApplier(threshold, discountRate);
	}

	@Bean
	ItemsBoughtCountDiscountApplier itemsBoughtCountDiscountApplier(@Value("${items.threshold:4}") int threshold,
											@Value("${items.discount:8}") double discountRate) {
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

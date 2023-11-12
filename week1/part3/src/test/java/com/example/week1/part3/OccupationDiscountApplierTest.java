package com.example.week1.part3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.week1.part3.assertion.PersonAssert.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OccupationDiscountApplierTest {
	@Mock RateRepository rateRepository;
	@Mock MessageSender messageSender;

	private static final double DISCOUNT_RATE = 10D;

	@Test
	public void should_send_discount_calculation_received_message_when_employed() {
		Person person = person(Occupation.EMPLOYED);

		applier().applyDiscount(person);

		verify(messageSender, times(1))
				.sendMessage(new Message(EventType.DISCOUNT_CALCULATION_RECEIVED));

	}

	@Test
	public void should_send_discount_calculation_received_message_when_occupation_unknown() {
		Person person = person(Occupation.DONT_KNOW);

		applier().applyDiscount(person);

		verify(messageSender, times(1))
				.sendMessage(new Message(EventType.DISCOUNT_CALCULATION_RECEIVED));

	}

	@Test
	public void should_not_send_discount_calculation_received_message_when_unemployed() {
		Person person = person(Occupation.UNEMPLOYED);

		applier().applyDiscount(person);

		verify(messageSender, never())
				.sendMessage(any());
	}

	@Test
	public void should_send_error_message_when_exception_accessing_rate_repository() {
		Person person = person(Occupation.EMPLOYED);

		when(rateRepository.getDiscountRate(Occupation.EMPLOYED)).thenThrow(new RuntimeException("Boom"));

		applier().applyDiscount(person);

		verify(messageSender, times(1))
				.sendMessage(new Message(EventType.ERROR));

	}

	@Test
	public void should_not_apply_discount_for_unemployed() {
		Person person = person(Occupation.UNEMPLOYED);

		applier().applyDiscount(person);

		verify(rateRepository, never()).getDiscountRate(any());
		assertThat(person).hasNoDiscount();
	}

	@Test
	public void should_apply_discount_for_employed() {
		Person person = person(Occupation.EMPLOYED);
		when(rateRepository.getDiscountRate(Occupation.EMPLOYED)).thenReturn(DISCOUNT_RATE);

		applier().applyDiscount(person);

		verify(rateRepository, times(1)).getDiscountRate(Occupation.EMPLOYED);
		assertThat(person).hasDiscountRateEqualTo(DISCOUNT_RATE);
	}

	@Test
	public void should_apply_discount_for_unknown_occupation() {
		Person person = person(Occupation.DONT_KNOW);
		when(rateRepository.getDiscountRate(Occupation.DONT_KNOW)).thenReturn(DISCOUNT_RATE);

		applier().applyDiscount(person);

		verify(rateRepository, times(1)).getDiscountRate(Occupation.DONT_KNOW);
		assertThat(person).hasDiscountRateEqualTo(DISCOUNT_RATE);
	}

	private Person person(Occupation occupation) {
		return new Person("Person", 42, occupation);
	}

	private OccupationDiscountApplier applier() {
		return new OccupationDiscountApplier(rateRepository, messageSender);
	}


}

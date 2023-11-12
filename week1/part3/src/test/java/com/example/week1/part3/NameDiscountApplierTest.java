package com.example.week1.part3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.week1.part3.assertion.PersonAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NameDiscountApplierTest {

	@Mock MessageSender messageSender;

	@Test
	void should_send_discount_calculated_message_when_name_long_enough() {
		Person person = person(NameDiscountApplier.NAME_LENGTH_THRESHOLD);

		applier().applyDiscount(person);

		verify(messageSender, times(1))
				.sendMessage(new Message(EventType.DISCOUNT_CALCULATED));

		assertThat(person).hasDiscountRateEqualTo(NameDiscountApplier.DISCOUNT_RATE);
	}

	@Test
	void should_apply_discount_when_name_length_equals_threshold() {
		Person person = person(NameDiscountApplier.NAME_LENGTH_THRESHOLD);

		applier().applyDiscount(person);

		assertThat(person).hasDiscountRateEqualTo(NameDiscountApplier.DISCOUNT_RATE);
	}

	@Test
	void should_apply_discount_when_name_length_longer_than_threshold() {
		Person person = person(NameDiscountApplier.NAME_LENGTH_THRESHOLD + 1);

		applier().applyDiscount(person);

		assertThat(person).hasDiscountRateEqualTo(NameDiscountApplier.DISCOUNT_RATE);
	}

	@Test
	void should_not_apply_discount_when_name_length_shorter_than_threshold() {
		Person person = person(NameDiscountApplier.NAME_LENGTH_THRESHOLD - 1);

		applier().applyDiscount(person);

		assertThat(person).hasNoDiscount();
	}

	private Person person(int nameLen) {
		return new Person("p".repeat(nameLen), 42, Occupation.EMPLOYED);
	}

	private NameDiscountApplier applier() {
		return new NameDiscountApplier(messageSender);
	}

}
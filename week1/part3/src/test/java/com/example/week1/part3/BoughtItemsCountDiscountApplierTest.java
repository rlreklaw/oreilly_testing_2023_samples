package com.example.week1.part3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.week1.part3.assertion.PersonAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BoughtItemsCountDiscountApplierTest {

	@Mock
	MessageSender messageSender;

	@Test
	public void should_send_discount_calculated_message_when_enough_goods() {
		Person person = person(BoughtItemsCountDiscountApplier.ITEM_COUNT_THRESHOLD);

		applier().applyDiscount(person);

		verify(messageSender, times(1))
				.sendMessage(new Message(EventType.DISCOUNT_CALCULATED));

		assertThat(person).hasDiscountRateEqualTo(BoughtItemsCountDiscountApplier.DISCOUNT_RATE);

	}

	@Test
	public void should_send_discount_calculated_message_when_too_few_items() {
		Person person = person(BoughtItemsCountDiscountApplier.ITEM_COUNT_THRESHOLD - 1);

		applier().applyDiscount(person);

		verify(messageSender, times(1))
				.sendMessage(new Message(EventType.DISCOUNT_CALCULATED));

		assertThat(person).hasNoDiscount();
	}

	@Test
	public void should_apply_discount_when_item_count_equals_threhsold() {
		Person person = person(BoughtItemsCountDiscountApplier.ITEM_COUNT_THRESHOLD);

		applier().applyDiscount(person);

		assertThat(person).hasDiscountRateEqualTo(BoughtItemsCountDiscountApplier.DISCOUNT_RATE);

	}

	@Test
	public void should_apply_discount_when_item_count_more_than_threhsold() {
		Person person = person(BoughtItemsCountDiscountApplier.ITEM_COUNT_THRESHOLD + 1);

		applier().applyDiscount(person);

		assertThat(person).hasDiscountRateEqualTo(BoughtItemsCountDiscountApplier.DISCOUNT_RATE);


	}

	@Test
	public void should_not_apply_discount_when_item_count_less_than_threhsold() {
		Person person = person(BoughtItemsCountDiscountApplier.ITEM_COUNT_THRESHOLD - 1);

		applier().applyDiscount(person);

		assertThat(person).hasNoDiscount();


	}

	private Person person(int itemCount) {
		return new Person("Person", itemCount, Occupation.EMPLOYED);
	}

	private BoughtItemsCountDiscountApplier applier() {
		return new BoughtItemsCountDiscountApplier(messageSender);
	}

}

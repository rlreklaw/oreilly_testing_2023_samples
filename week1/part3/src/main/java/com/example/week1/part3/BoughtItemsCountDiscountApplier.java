package com.example.week1.part3;

public class BoughtItemsCountDiscountApplier implements DiscountApplier {
	public static final int ITEM_COUNT_THRESHOLD = 5;
	public static final double DISCOUNT_RATE = 5D;

	private final MessageSender messageSender;

	public BoughtItemsCountDiscountApplier(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	@Override
	public void applyDiscount(Person person) {
		messageSender.sendMessage(new Message(EventType.DISCOUNT_CALCULATION_RECEIVED));
		System.out.println("Calculating number of goods discount");
		person.setDiscountRate(person.getDiscountRate() + rate(person));
		// THIS EVENT MUST BE CALLED AFTER RECEIVED!!
		messageSender.sendMessage(new Message(EventType.DISCOUNT_CALCULATED));
	}

	private static double rate(Person person) {
		if (person.getNumberOfBoughtGoods() < ITEM_COUNT_THRESHOLD) {
			return 0D;
		}
		return DISCOUNT_RATE;
	}
}

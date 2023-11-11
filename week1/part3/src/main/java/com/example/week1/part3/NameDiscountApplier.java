package com.example.week1.part3;

public class NameDiscountApplier implements DiscountApplier {
	public static final int NAME_LENGTH_THRESHOLD = 3;
	public static final double DISCOUNT_RATE = 8D;

	private final MessageSender messageSender;

	public NameDiscountApplier(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	@Override
	public void applyDiscount(Person person) {
		System.out.println("Calculating name discount");
		person.setDiscountRate(person.getDiscountRate() + rate(person));
		messageSender.sendMessage(new Message(EventType.DISCOUNT_CALCULATED));
	}

	private double rate(Person person) {
		if (person.getName().length() < NAME_LENGTH_THRESHOLD) { // most likely a prank!
			return 0D;
		}
		return DISCOUNT_RATE;
	}
}

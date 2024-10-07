package com.mcann.utility.enums;

// KART TİPLERİ
public enum CardType {
	STANDARD(1),
	DISCOUNTED(0),
	TEACHER(0.4),
	STUDENT(0.5),
	ELDERLY(0.4),
	DISABLED(0);
	
	private final double discountRate;
	CardType(double discountRate) {
		this.discountRate = discountRate;
	}
	
	public double getDiscountRate() {
		return discountRate;
	}
}
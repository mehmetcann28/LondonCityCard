package com.mcann.utility.enums;

// KART TİPLERİ
public enum CardType {
	STANDARD(1,2),
	DISCOUNTED(0,2),
	TEACHER(0.6,1),
	STUDENT(0.5,1),
	ELDERLY(0.6,5),
	DISABLED(0,10);
	
	private final int validityYears;
	private final double discountRate;
	CardType(double discountRate, int validityYears) {
		this.discountRate = discountRate;
		this.validityYears = validityYears;
	}
	
	public double getDiscountRate() {
		return discountRate;
	}
	
	public int getValidityYears() {
		return validityYears;
	}
}
package com.mcann.utility;

public class Constant {
	public static final Double STANDARTFEE = 20.0;
	public static final Double FIRSTTRANSFERFEE = STANDARTFEE - (STANDARTFEE * 0.29);
	public static final Double SECONDTRANSFERFEE = FIRSTTRANSFERFEE - (FIRSTTRANSFERFEE * 0.29);
	public static final Double THIRDTRANSFERFEE = SECONDTRANSFERFEE - (SECONDTRANSFERFEE * 0.29);
}
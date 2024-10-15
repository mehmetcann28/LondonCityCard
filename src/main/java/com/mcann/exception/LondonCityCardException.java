package com.mcann.exception;

import lombok.Getter;

@Getter
public class LondonCityCardException extends RuntimeException {
	private ErrorType errorType;
	public LondonCityCardException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
}
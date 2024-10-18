package com.mcann.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	INTERNAL_SERVER_ERROR(500, "Sunucuda beklenmeyen bir hata oldu. Lütfen tekrar deneyin", HttpStatus.INTERNAL_SERVER_ERROR),
	VALIDATION_ERROR(400,"girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyin.", HttpStatus.BAD_REQUEST),
	USER_NOT_FOUND(4001,"Kullanıcı bulunamadı", HttpStatus.BAD_REQUEST),
	INVALID_USERNAME_OR_PASSWORD(4002,"Kullanıcı adı ya da şifre hatalıdır",HttpStatus.BAD_REQUEST),
	INVALIDCARDTYPE_EXCEPTION(5001, "Bu kart tipi için kullanıcı kaydı gereklidir.", HttpStatus.BAD_REQUEST),
	YETERSIZ_BAKIYE_HATASI(5002,"Yetersiz bakiye. Lütfen kartınıza para yükleyin.",HttpStatus.BAD_REQUEST),
	CARD_NOT_FOUND(5003,"Kart bulunamadı",HttpStatus.BAD_REQUEST),
	TRANSITION_NOT_FOUND(6001,"Geçersiz işlem türü.",HttpStatus.BAD_REQUEST),
	STATION_NOT_FOUND(7001,"Durak bulunamadı",HttpStatus.BAD_REQUEST),
	CARD_USAGE_NOT_FOUND(8001,"Son kart kullanımı bulunamadı",HttpStatus.BAD_REQUEST),
	PAYMENT_CARD_NOT_FOUND(3001,"Kredi veya banka kartı bulunamadı",HttpStatus.BAD_REQUEST),
	INVALID_TOKEN(9001,"Geçersiz token bilgisi",HttpStatus.BAD_REQUEST),
	INVALID_PASSWORD(8002,"Şifreler uyuşmamaktadır",HttpStatus.BAD_REQUEST);
	
	int code;
	String message;
	HttpStatus httpStatus;
}
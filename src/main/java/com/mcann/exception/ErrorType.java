package com.mcann.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	INTERNAL_SERVER_ERROR(500, "Sunucuda beklenmeyen bir hata oldu. Lütfen tekrar deneyin", HttpStatus.INTERNAL_SERVER_ERROR),
	VALIDATION_ERROR(400,"girilen parametreler hatalıdır. Lütfen kontrol ederek tekrar deneyin.", HttpStatus.BAD_REQUEST),
	INVALIDCARDTYPE_EXCEPTION(5001, "Bu kart tipi için kullanıcı kaydı gereklidir.", HttpStatus.BAD_REQUEST),
	YETERSIZ_BAKIYE_HATASI(5002,"Yetersiz bakiye. Lütfen kartınıza para yükleyin.",HttpStatus.BAD_REQUEST),
	CARD_NOT_FOUND(5003,"Kart bulunamadı",HttpStatus.BAD_REQUEST),
	TRANSITION_NOT_FOUND(6001,"Geçersiz işlem türü.",HttpStatus.BAD_REQUEST),
	STATION_NOT_FOUND(7001,"Durak bulunamadı",HttpStatus.BAD_REQUEST),
	INVALID_PASSWORD(8001,"Şifreler uyuşmamaktadır",HttpStatus.BAD_REQUEST);
	
	
	
	int code;
	String message;
	HttpStatus httpStatus;
}
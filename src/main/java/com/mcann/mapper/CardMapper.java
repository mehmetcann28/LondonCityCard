package com.mcann.mapper;

import com.mcann.dto.request.AddCardRequestDto;
import com.mcann.dto.request.BalanceLoadCardRequestDto;
import com.mcann.entity.Card;
import com.mcann.utility.enums.CardType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy =
		NullValuePropertyMappingStrategy.IGNORE)
public interface CardMapper {
	CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
	@Mapping(target = "cardNumber",expression = "java(generateCardNumber())")
	@Mapping(target = "cvv",expression = "java(generateCvv())")
	@Mapping(target = "expiryDate", expression = "java(generateExpiryDate(cardType))")
	@Mapping(target = "cardType", source = "cardType")
	Card addUserCard(final CardType cardType);
	
	@Mapping(target = "cardNumber",expression = "java(generateCardNumber())")
	@Mapping(target = "cvv",expression = "java(generateCvv())")
	@Mapping(target = "expiryDate", expression = "java(generateExpiryDate(dto.cardType()))")
	@Mapping(target = "cardType", source = "dto.cardType")
	Card addCard(final AddCardRequestDto dto);
	
	@Mapping(target = "balance", expression = "java(card.getBalance() + dto.amount())")
	Card updateBalanceFromDto(final BalanceLoadCardRequestDto dto, Card card);
	
	default String generateCardNumber() {
		return convertToNumeric(UUID.randomUUID().toString().substring(0, 16));
	}
	
	default String generateCvv() {
		return convertToNumeric(UUID.randomUUID().toString().substring(0, 3));
	}
	
	default LocalDate generateExpiryDate(CardType cardType) {
		return LocalDate.now().plusYears(cardType.getValidityYears());
	}
	
	default String convertToNumeric(String input) {
		StringBuilder numericString = new StringBuilder();
		int count = 0;
		for (char c : input.toCharArray()) {
			numericString.append((int) c % 10); // ASCII değerlerini sayısal karakterlere dönüştürme
			count++;
			
			if (count % 4 == 0 && count != input.length()) {
				numericString.append(" ");
			}
		}
		return numericString.toString();
	}
}
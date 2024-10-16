package com.mcann.mapper;

import com.mcann.dto.request.AddCardRequestDto;
import com.mcann.dto.request.DisableCardRequestDto;
import com.mcann.entity.Card;
import com.mcann.utility.enums.CardType;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy =
		NullValuePropertyMappingStrategy.IGNORE)
public interface CardMapper {
	CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
	Card addUserCard(final CardType cardType);
	Card addCard(final AddCardRequestDto dto);
}
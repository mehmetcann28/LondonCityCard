package com.mcann.mapper;

import com.mcann.dto.request.AddPaymentCardRequestDto;
import com.mcann.entity.PaymentCard;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentCardMapper {
	PaymentCardMapper INSTANCE = Mappers.getMapper(PaymentCardMapper.class);
//	@Mapping(target = "paymentType", expression = "java(PaymentType.CARD)")
	PaymentCard addPaymentCard(final AddPaymentCardRequestDto dto);
}
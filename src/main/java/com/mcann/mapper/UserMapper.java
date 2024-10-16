package com.mcann.mapper;

import com.mcann.dto.request.RegisterRequestDto;
import com.mcann.dto.request.UpdateUserProfileRequestDto;
import com.mcann.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,nullValuePropertyMappingStrategy =
		NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	User registerUser(final RegisterRequestDto dto);
//	@Mappings({
//			@Mapping(source = "isim",target = "firstName"),
//			@Mapping(source = "soyisim", target = "lastName"),
//	})
//	@Mappings({
//			@Mapping(source = "isim",target = "firstName"),
//			@Mapping(source = "soyisim", target = "lastName"),
////			@Mapping(target = "customField", expression = "java(mapCustomField(dto.customField()))")
////			@Mapping(target = "birthday", expression = "java(mapBirthday(dto.birthday()))"),
//	})
	

	User updateUser(final UpdateUserProfileRequestDto dto, @MappingTarget User user);
	
//	default String mapCustomField(String customField) {
//		// customField değerine özel dönüşüm işlemi
//		return customField != null ? customField.toUpperCase() : null;
//	}
	
	default LocalDate mapBirthday(int birthdayYear) {
		// Yılı alıp sabit bir gün ve ay ile LocalDate oluşturuyoruz (01 Ocak)
		 return LocalDate.of(birthdayYear, 1, 1); }
}
package com.mcann.service;

import com.mcann.dto.request.RegisterRequestDto;
import com.mcann.dto.request.UpdateUserProfileRequestDto;
import com.mcann.entity.Card;
import com.mcann.entity.User;
import com.mcann.exception.ErrorType;
import com.mcann.exception.LondonCityCardException;
import com.mcann.mapper.UserMapper;
import com.mcann.repository.UserRepository;
import com.mcann.utility.enums.CardType;
import com.mcann.views.VwUser;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
	private final UserRepository userRepository;
	private final CardService cardService;
	
//	public User addUser(String firstName, String lastName, String email, String password, String phone,
//	                    String address, String username, LocalDate birthday,CardType cardType) {
//		Card card = cardService.addUserCard(cardType);
//		User user = User.builder()
//				.firstName(firstName)
//				.lastName(lastName)
//				.address(address)
//				.email(email)
//				.birthday(birthday)
//				.username(username)
//				.password(password)
//				.phone(phone)
//				.cardId(card.getId())
//				.cardType(cardType)
//				        .build();
//		return userRepository.save(user);
//	}
//
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public List<VwUser> getAllVwUser() {
		return userRepository.getAllVwUsers();
	}
	
	//TODO Turist geldi standart kart aldı bunun için user bilgileri istenmesin.
	public void register(RegisterRequestDto dto){
		User user = UserMapper.INSTANCE.registerUser(dto);
		user.setCardId(cardService.addUserCard(dto.cardType()).getId());
		userRepository.save(user);
	}
	
//	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
//	@Mappings({
//     @Mapping(source = "kullaniciAdi", target = "username"),
//			@Mapping(source = "sifre",target = "password"),
//
//	})
//@Mapping(source = "kullaniciAdi", target = "username")
	public User update(UpdateUserProfileRequestDto dto){
		User user = userRepository.findById(dto.id())
		                          .orElseThrow(() -> new LondonCityCardException(ErrorType.USER_NOT_FOUND));
		return userRepository.save(UserMapper.INSTANCE.updateUser(dto, user));
	}
	
}
package com.mcann.service;

import com.mcann.dto.request.RegisterRequestDto;
import com.mcann.dto.request.UpdateUserProfileRequestDto;
import com.mcann.entity.Card;
import com.mcann.entity.User;
import com.mcann.mapper.UserMapper;
import com.mcann.repository.UserRepository;
import com.mcann.utility.enums.CardType;
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
	//TODO Turist geldi standart kart aldı bunun için user bilgileri istenmesin.
	public User addUser(String firstName, String lastName, String email, String password, String phone,
	                    String address, String username, LocalDate birthday,CardType cardType) {
		Card card = cardService.addUserCard(cardType);
		User user = User.builder()
				.firstName(firstName)
				.lastName(lastName)
				.address(address)
				.email(email)
				.birthday(birthday)
				.username(username)
				.password(password)
				.phone(phone)
				.cardId(card.getId())
				.cardType(cardType)
				        .build();
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public void register(RegisterRequestDto dto){
		userRepository.save(UserMapper.INSTANCE.registerUser(dto));
	}
	
//	nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
	/*@Mappings({
			@Mapping(source = "isim",target = "firstName"),
			@Mapping(source = "soyisim", target = "lastName"),
			
	})
	public User update(UpdateUserProfileRequestDto dto){
	
	}*/
	
}
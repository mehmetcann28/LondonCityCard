package com.mcann.utility;

import com.mcann.service.CardService;
import com.mcann.service.TransactionService;
import com.mcann.service.UserService;
import com.mcann.utility.enums.CardType;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements ApplicationRunner {
	private final CardService cardService;
	private final UserService userService;
	private final TransactionService transactionService;
	
	public DataGenerator(CardService cardService, UserService userService, TransactionService transactionService) {
		this.cardService = cardService;
		this.userService = userService;
		this.transactionService = transactionService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		addCard();
		addUser();
	}
	public String addCard() {
		cardService.addCard(1L,"1234 3214 9654 8523", 50.0, LocalDate.of(2025, 10, 10), "050", CardType.STANDARD);
		cardService.addCard(1L,"3214 3214 8546 8523", 70.0, LocalDate.of(2025, 8, 8), "052", CardType.STUDENT);
		cardService.addCard(2L,"8526 3214 3256 8523", 90.0, LocalDate.of(2025, 5, 5), "054", CardType.ELDERLY);
		return "Card bilgileri eklendi.";
	}
	
	public String addUser() {
		userService.addUser("Mehmet", "Tufan", "m.tufan@gmail.com", "123456", "05225473366", "Adana", "mtufan", LocalDate.of(1992,10,10));
		userService.addUser("Ozkan","Sargin","o.sargin@gmail.com","123456","05356998877","Karadag","osargin",LocalDate.of(1991,02,14));
		userService.addUser("Mehmet Can","Karahan","mcan@gmail.com","123456","05368254788","Istanbul","mcan",LocalDate.of(2000,06,19));
		return "Kullanicilar eklendi";
	}
}
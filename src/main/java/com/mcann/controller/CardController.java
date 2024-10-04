package com.mcann.controller;

import com.mcann.entity.Card;
import com.mcann.service.CardService;
import com.mcann.utility.enums.CardType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
	private final CardService cardService;
	
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}
//	localhost:9090/card/add-card
	@GetMapping("/add-card")
	public String addCard() {
		cardService.addCard("1234 3214 9654 8523", 50.0, LocalDate.of(2025,10,10), 050, CardType.STANDARD);
		cardService.addCard("3214 3214 8546 8523", 70.0, LocalDate.of(2025,8,8), 052, CardType.STUDENT);
		cardService.addCard("8526 3214 3256 8523", 90.0, LocalDate.of(2025,5,5), 054, CardType.ELDERLY);
		cardService.addCard("4567 3214 2145 8523", 40.0, LocalDate.of(2025,11,11), 056, CardType.STANDARD);
		return "Card bilgileri eklendi.";
	}
	
	// http://localhost:9090/card/get-all-cards
	@GetMapping("/get-all-cards")
	public List<Card> getAllCards() {
		return cardService.getAllCards();
	}
}
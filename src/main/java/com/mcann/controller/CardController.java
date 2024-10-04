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
	
	// http://localhost:9090/card/get-all-cards
	@GetMapping("/get-all-cards")
	public List<Card> getAllCards() {
		return cardService.getAllCards();
	}
}
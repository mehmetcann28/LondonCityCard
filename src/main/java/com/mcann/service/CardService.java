package com.mcann.service;

import com.mcann.entity.Card;
import com.mcann.repository.CardRepository;
import com.mcann.utility.enums.CardType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CardService {
	private final CardRepository cardRepository;
	
	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	public void addCard(String cardNumber, Double balance, LocalDate expiryDate, Integer cvv, CardType cardType) {
		Card card =
				Card.builder()
				    .cardNumber(cardNumber)
				    .balance(balance)
				    .expiryDate(expiryDate).cvv(cvv).cardType(cardType).build();
		cardRepository.save(card);
	}
	
	public List<Card> getAllCards() {
		return cardRepository.findAll();
	}
}
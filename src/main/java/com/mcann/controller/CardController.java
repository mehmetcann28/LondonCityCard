package com.mcann.controller;

import com.mcann.constant.RestApis;

import static com.mcann.constant.RestApis.*;

import com.mcann.dto.response.BaseResponse;
import com.mcann.entity.Card;
import com.mcann.service.CardService;
import com.mcann.utility.enums.CardType;
import com.mcann.views.VwCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(CARD)
public class CardController {
	private final CardService cardService;
	
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}
	
	// http://localhost:9090/card/get-all-cards
	@GetMapping(GETALLCARDS)
	public List<Card> getAllCards() {
		return cardService.getAllCards();
	}
	
	@GetMapping(GETALLVWCARDS)
	public ResponseEntity<BaseResponse<List<VwCard>>> getAllVwCards() {
		return ResponseEntity.ok(BaseResponse.<List<VwCard>>builder()
		                                     .success(true)
		                                     .message("Liste başarılı bir şekilde getirildi")
		                                     .code(404)
		                                     .data(cardService.getAllVwCards()).build());
	}
	
	@PostMapping(POSTADDCARD)
	public String addCard(String cardNumber, String cvv, CardType cardType) {
		cardService.addCard(cardNumber,cvv,cardType);
		return "Kayıt başarılı";
	}
}
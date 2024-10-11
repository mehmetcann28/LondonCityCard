package com.mcann.controller;

import static com.mcann.constant.RestApis.*;

import com.mcann.dto.response.BaseResponse;
import com.mcann.entity.Card;
import com.mcann.service.CardService;
import com.mcann.views.VwCard;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(CARD)
@RequiredArgsConstructor
public class CardController {
	private final CardService cardService;
	
	// http://localhost:9090/card/get-all-cards
	@GetMapping(GETALLCARDS)
	public List<Card> getAllCards() {
		return cardService.getAllCards();
	}
	
	@GetMapping(GETALLVWCARDS)
	public ResponseEntity<BaseResponse<List<VwCard>>> getAllVwCards() {
		return ResponseEntity.ok(BaseResponse.<List<VwCard>>builder().success(true)
		                                     .message("Liste başarılı bir şekilde getirildi").code(404)
		                                     .data(cardService.getAllVwCards()).build());
	}
	
	//TODO bu işlem user controller da çalışacak
	/*@PostMapping(POSTADDCARD)
	public String addCard(String cardNumber, String cvv, CardType cardType) {
		cardService.addCard(cardNumber,cvv,cardType);
		return "Kayıt başarılı";
	}*/
}
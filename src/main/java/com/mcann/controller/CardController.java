package com.mcann.controller;

import static com.mcann.constant.RestApis.*;

import com.mcann.dto.request.*;
import com.mcann.dto.response.BaseResponse;
import com.mcann.entity.Card;
import com.mcann.service.CardService;
import com.mcann.service.CardUsageService;
import com.mcann.views.VwCardNotUser;
import com.mcann.views.VwCardType;
import com.mcann.views.VwCardUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CARD)
@RequiredArgsConstructor
public class CardController {
	private final CardService cardService;
	private final CardUsageService cardUsageService;
	
	@PostMapping(ADD)
	public ResponseEntity<BaseResponse<Boolean>> addCard(@RequestBody AddCardRequestDto dto){
		cardService.addCard(dto);
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
							.code(200)
							.message("Kart başarıyla kaydedildi")
							.success(true)
							.data(true)
						    .build()
		);
	}
	
	@DeleteMapping(DELETE)
	public ResponseEntity<BaseResponse<Boolean>> deleteCard(@RequestBody Long id){
		cardService.deleteCardById(id);
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
				            .code(200)
				            .message("Kart başarıyla silindi.")
				            .success(true)
				            .data(true)
				            .build()
		);
	}
	
	@PostMapping(FINDBYID)
	public ResponseEntity<BaseResponse<Card>> findById(@RequestBody Long id){
		Card cardById = cardService.findAll(id);
		return ResponseEntity.ok(
				BaseResponse.<Card>builder()
				            .code(200)
				            .message("Kart bilgileri başarıyla getirildi.")
				            .success(true)
				            .data(cardById)
				            .build()
		);
	}
	
	@PostMapping(BALANCELOAD)
	public ResponseEntity<BaseResponse<Card>> loadBalance(@RequestBody BalanceLoadCardRequestDto dto){
		Card card = cardService.balanceLoadCard(dto);
		return ResponseEntity.ok(
				BaseResponse.<Card>builder()
				            .code(200)
				            .message("Kart bakiyesi başarıyla yüklendi.")
				            .success(true)
				            .data(card)
				            .build()
		);
	}
	
	@PostMapping(BALANCEDEDUCTION)
	public ResponseEntity<BaseResponse<Boolean>> cardUsageBalanceDeduction(@RequestBody CardUsageBalaceDeductionRequestDto dto){
		cardService.cardUsageBalanceDeductionCard(dto);
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
				            .code(200)
				            .message("Kart başarılı bir şekilde kullanıldı")
				            .success(true)
				            .data(true)
				            .build()
		);
	}
	
	@GetMapping(FINDALLVWUSERCARDS)
	public ResponseEntity<BaseResponse<List<VwCardUser>>> getUserVwCards() {
		return ResponseEntity.ok(BaseResponse.<List<VwCardUser>>builder()
		                                     .success(true)
		                                     .message("Liste başarılı bir şekilde getirildi")
		                                     .code(200)
		                                     .data(cardService.getUserVwCards())
		                                     .build()
		);
	}
	
	@GetMapping(FINDALLVWNOTUSERCARDS)
	public ResponseEntity<BaseResponse<List<VwCardNotUser>>> getNotUserVwCards() {
		return ResponseEntity.ok(
				BaseResponse.<List<VwCardNotUser>>builder()
				            .success(true)
				            .data(cardService.getNotUserVwCards())
				            .message("Liste başarılı bir şekilde getirildi")
				            .code(200)
				            .build()
		);
	}
	
	@GetMapping(FINDALLVWSTANDARDCARDS)
	public ResponseEntity<BaseResponse<List<VwCardType>>> getStandardVwCards() {
		return ResponseEntity.ok(
				BaseResponse.<List<VwCardType>>builder()
							.data(cardService.getStandardVwCards())
							.success(true)
							.message("Liste başarılı bir şekilde getirildi")
							.code(200)
							.build()
		);
	}
	
	@GetMapping(FINDALLVWSTUDENTCARDS)
	public ResponseEntity<BaseResponse<List<VwCardType>>> getStudentVwCards() {
		return ResponseEntity.ok(
				BaseResponse.<List<VwCardType>>builder()
				            .data(cardService.getStudentVwCards())
				            .success(true)
				            .message("Liste başarılı bir şekilde getirildi")
				            .code(200)
				            .build()
		);
	}
	
	@GetMapping(FINDALLVWTEACHERCARDS)
	public ResponseEntity<BaseResponse<List<VwCardType>>> getTeacherVwCards() {
		return ResponseEntity.ok(
				BaseResponse.<List<VwCardType>>builder()
				            .data(cardService.getTeacherVwCards())
				            .success(true)
				            .message("Liste başarılı bir şekilde getirildi")
				            .code(200)
				            .build()
		);
	}
	
	@GetMapping(FINDALLVWELDERLYCARDS)
	public ResponseEntity<BaseResponse<List<VwCardType>>> getElderlyVwCards() {
		return ResponseEntity.ok(
				BaseResponse.<List<VwCardType>>builder()
				            .data(cardService.getElderlyVwCards())
				            .success(true)
				            .message("Liste başarılı bir şekilde getirildi")
				            .code(200)
				            .build()
		);
	}
	
	@GetMapping(FINDALLVWDISCOUNTEDCARDS)
	public ResponseEntity<BaseResponse<List<VwCardType>>> getDiscountedVwCards() {
		return ResponseEntity.ok(
				BaseResponse.<List<VwCardType>>builder()
				            .data(cardService.getDiscountedVwCards())
				            .success(true)
				            .message("Liste başarılı bir şekilde getirildi")
				            .code(200)
				            .build()
		);
	}
	
	@GetMapping(FINDALLACTIVECARDS)
	public ResponseEntity<BaseResponse<List<Card>>> getActiveCards() {
		return ResponseEntity.ok(
				BaseResponse.<List<Card>>builder()
				            .data(cardService.getActiveCards())
				            .success(true)
				            .message("Liste başarılı bir şekilde getirildi")
				            .code(200)
				            .build()
		);
	}
	
	@GetMapping(FINDALLPASSIVECARDS)
	public ResponseEntity<BaseResponse<List<Card>>> getPassiveCards() {
		return ResponseEntity.ok(
				BaseResponse.<List<Card>>builder()
				            .data(cardService.getPassiveCards())
				            .success(true)
				            .message("Liste başarılı bir şekilde getirildi")
				            .code(200)
				            .build()
		);
	}
	
	@PostMapping(DISABLED)
	public ResponseEntity<BaseResponse<Boolean>> setDisableCard(DisableCardRequestDto dto){
		cardService.setDisabledCard(dto);
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
							.code(200)
							.message("Kart başarıyla pasif duruma getirildi.")
							.data(true)
							.success(true)
						    .build()
		);
	}
	
	// http://localhost:9090/card/get-all-cards
	@GetMapping(GETALLCARDS)
	public List<Card> getAllCards() {
		return cardService.getAllCards();
	}
	
	/*@GetMapping(GETCARDUSAGE)
	public CardUsage getCardUsage() {
		Optional<CardUsage> byCardId = cardUsageService.findByCardId(1L, TransitionType.INITIAL_USAGE);
		return byCardId.get();
	}*/
	
}
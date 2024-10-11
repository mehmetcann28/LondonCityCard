package com.mcann.utility;

import com.mcann.service.*;
import com.mcann.utility.enums.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements ApplicationRunner {
	private final CardService cardService;
	private final UserService userService;
	private final StationService stationService;
	private final LineService lineService;
	
	public DataGenerator(CardService cardService, UserService userService, StationService stationService, LineService lineService) {
		this.cardService = cardService;
		this.userService = userService;
		this.stationService = stationService;
		this.lineService = lineService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		addCard();
//		addUser();
//		balanceLoadCard();
//		addStation();
//		addLine();
		cardUsageBalanceDeductionCard();
	}
	public String addCard() {
		cardService.addCard(CardType.STANDARD);
//		cardService.addCard(1L,"1234 3214 9654 8523", "050", CardType.STANDARD);
//		cardService.addCard(1L,"3214 3214 8546 8523", "052", CardType.STUDENT);
//		cardService.addCard(2L,"8526 3214 3256 8523", "054", CardType.ELDERLY);
//		cardService.addCard(null,"1236 5478 9563 1234","561", CardType.STANDARD);
		return "Card bilgileri eklendi.";
	}
	
	public String addUser(){
		userService.addUser("Mehmet", "Tufan", "m.tufan@gmail.com", "123456", "05225473366", "Adana", "mtufan",
		                    LocalDate.of(1992,10,10),CardType.STANDARD);
		userService.addUser("Ozkan","Sargin","o.sargin@gmail.com","123456","05356998877","Karadag","osargin",
		                    LocalDate.of(1991,2,14),CardType.ELDERLY);
		userService.addUser("Mehmet Can","Karahan","mcan@gmail.com","123456","05368254788","Istanbul","mcan",
		                    LocalDate.of(2000,6,19),CardType.STUDENT);
		return "Kullanicilar eklendi";
	}
	
	public String balanceLoadCard() throws Exception {
		cardService.balanceLoadCard(1L,250d,PaymentType.CASH);
		cardService.balanceLoadCard(2L,500d,PaymentType.CASH);
		cardService.balanceLoadCard(3L,100d,PaymentType.CASH);
		cardService.balanceLoadCard(4L,100d,PaymentType.CASH);
		return "Para Yuklendi";
	}
	public String cardUsageBalanceDeductionCard() throws Exception {
		cardService.cardUsageBalanceDeductionCard(2L,1L,PaymentType.CARD,TransitionType.INITIAL_USAGE);
//		cardService.cardUsageBalanceDeductionCard(2L,3L,PaymentType.CARD,TransitionType.TRANSFER);
//		cardService.cardUsageBalanceDeductionCard(3L,5L,PaymentType.CARD,TransitionType.TRANSFER);
//		cardService.cardUsageBalanceDeductionCard(1L,6L,PaymentType.CARD,TransitionType.TRANSFER);
		cardService.cardUsageBalanceDeductionCard(2L,6L,PaymentType.CARD,TransitionType.TRANSFER);
		cardService.cardUsageBalanceDeductionCard(2L,5L,PaymentType.CARD,TransitionType.TRANSFER);
		cardService.cardUsageBalanceDeductionCard(2L,3L,PaymentType.CARD,TransitionType.TRANSFER);
//		cardService.cardUsageBalanceDeductionCard(1L,1L,PaymentType.CARD,TransitionType.TRANSFER);
		
//		cardService.cardUsageBalanceDeductionCard(3L,1L,PaymentType.CARD,TransitionType.INITIAL_USAGE);
//		cardService.cardUsageBalanceDeductionCard(3L,2L,PaymentType.CARD,TransitionType.TRANSFER);
//		cardService.cardUsageBalanceDeductionCard(3L,1L,PaymentType.CARD,TransitionType.INITIAL_USAGE);
//		cardService.cardUsageBalanceDeductionCard(3L,2L,PaymentType.CARD,TransitionType.TRANSFER);
		return "Bakiyenizden para harcandı";
	}
	
	public String addStation(){
		stationService.addStation("BeylikduzuSonDurak", "B-1", StationType.METROBUS,"Beylikduzu");
		stationService.addStation("Avcilar","A-3", StationType.METROBUS,"Avcilar");
		stationService.addStation("Cevatpaşa","C-1",StationType.BUS,"Bayrampasa");
		stationService.addStation("Eminönü","E-1",StationType.BUS,"Fatih");
		stationService.addStation("Bereç","B-2",StationType.BUS,"Gaziosmanpaşa");
		stationService.addStation("Vezneciler","V-1",StationType.BUS,"Fatih");
		return "Metrobus duraklari eklendi";
	}
	
	public String addLine(){
		lineService.addLine(1L,2L,"34A",VehicleType.METROBUS);
		lineService.addLine(2L,1L,"34B",VehicleType.METROBUS);
		lineService.addLine(3L,4L,"32A",VehicleType.BUS);
		lineService.addLine(5L,4L,"336E",VehicleType.BUS);
		lineService.addLine(6L,5L,"36KE",VehicleType.BUS);
		return "Metrobus hatti eklendi";
	}
}
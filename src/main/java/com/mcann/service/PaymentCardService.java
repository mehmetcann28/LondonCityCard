package com.mcann.service;

import com.mcann.dto.request.AddPaymentCardRequestDto;
import com.mcann.entity.PaymentCard;
import com.mcann.exception.ErrorType;
import com.mcann.exception.LondonCityCardException;
import com.mcann.mapper.PaymentCardMapper;
import com.mcann.repository.PaymentCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentCardService {
	private final PaymentCardRepository paymentCardRepository;
	
	public Optional<PaymentCard> getPaymentCardById(Long id) {
		return paymentCardRepository.findById(id);
	}
	
	public void addPaymentCard(AddPaymentCardRequestDto dto){
		paymentCardRepository.save(PaymentCardMapper.INSTANCE.addPaymentCard(dto));
	}
	
	public Long addPaymentCardAndReturnId(AddPaymentCardRequestDto dto) {
		PaymentCard paymentCard = PaymentCardMapper.INSTANCE.addPaymentCard(dto);
		paymentCardRepository.save(paymentCard);
		return paymentCard.getId();  // Kartın ID'sini geri döndürüyoruz
	}
}
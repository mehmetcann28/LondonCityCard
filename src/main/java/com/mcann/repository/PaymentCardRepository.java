package com.mcann.repository;

import com.mcann.entity.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {
	Optional<PaymentCard> getPaymentCardById(Long id);
}
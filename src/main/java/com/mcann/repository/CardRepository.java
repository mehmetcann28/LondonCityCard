package com.mcann.repository;

import com.mcann.entity.Card;
import com.mcann.views.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
	@Query("select new com.mcann.views.VwCardUser(u.firstName, u.lastName, c.balance, c.expiryDate, c.cardType) " +
			"from Card c join User u on u.cardId = c.id")
	List<VwCardUser> findUserCards();
	
	@Query("SELECT new com.mcann.views.VwCardNotUser(c.cardNumber, c.balance, c.expiryDate, c.cardType) " +
			"FROM Card c LEFT JOIN User u ON u.cardId = c.id " +
			"WHERE u.cardId IS NULL")
	List<VwCardNotUser> findNotUserCards();
	
	@Query("SELECT new com.mcann.views.VwCardType(u.firstName, u.lastName,c.cardNumber, c.balance,c.expiryDate,c.cardType) " +
			"FROM Card c LEFT JOIN User u ON u.cardId = c.id " +
			"WHERE c.cardType = 'STANDARD'")
	List<VwCardType> findStandardCards();
	
	@Query("SELECT new com.mcann.views.VwCardType(u.firstName, u.lastName,c.cardNumber, c.balance,c.expiryDate,c.cardType) " +
			"FROM Card c LEFT JOIN User u ON u.cardId = c.id " +
			"WHERE c.cardType = 'STUDENT'")
	List<VwCardType> findStudentCards();
	
	@Query("SELECT new com.mcann.views.VwCardType(u.firstName, u.lastName,c.cardNumber, c.balance,c.expiryDate,c.cardType) " +
			"FROM Card c LEFT JOIN User u ON u.cardId = c.id " +
			"WHERE c.cardType = 'TEACHER'")
	List<VwCardType> findTeacherCards();
	
	@Query("SELECT new com.mcann.views.VwCardType(u.firstName, u.lastName,c.cardNumber, c.balance,c.expiryDate,c.cardType) " +
			"FROM Card c LEFT JOIN User u ON u.cardId = c.id " +
			"WHERE c.cardType = 'ELDERLY'")
	List<VwCardType> findElderlyCards();
	
	@Query("SELECT new com.mcann.views.VwCardType(u.firstName, u.lastName,c.cardNumber, c.balance,c.expiryDate,c.cardType) " +
			"FROM Card c LEFT JOIN User u ON u.cardId = c.id " +
			"WHERE c.cardType = 'DISCOUNTED'")
	List<VwCardType> findDiscountedCards();
	
	@Query("SELECT c FROM Card c WHERE c.state = 'ACTIVE'")
	List<Card> findActiveCards();
	
	@Query("SELECT c FROM Card c WHERE c.state = 'PASSIVE'")
	List<Card> findPassiveCards();
	
	Optional<Card> getCardById(Long id);
	
}
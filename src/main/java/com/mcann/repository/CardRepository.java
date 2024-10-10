package com.mcann.repository;

import com.mcann.entity.Card;
import com.mcann.views.VwCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
	@Query("select new com.mcann.views.VwCard(u.firstName, u.lastName, c.balance, c.expiryDate, c.cardType) " +
			"from Card c join User u on u.cardId = c.id")
	List<VwCard> getAllCard();
}
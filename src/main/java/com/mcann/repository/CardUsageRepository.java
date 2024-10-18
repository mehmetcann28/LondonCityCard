package com.mcann.repository;

import com.mcann.entity.CardUsage;
import com.mcann.utility.enums.TransitionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardUsageRepository extends JpaRepository<CardUsage, Long> {
//	CardUsage findFirstByCardIdAndTransitionTypeOrderByCreatedAtDesc(Long cardId, TransitionType transitionType);
	
	// Belirli bir transitionType ile kart kullanımını bulma
	@Query("select c from CardUsage c where c.cardId = ?1 and c.transitionType = ?2 order by c.createAt desc limit 1")
	Optional<CardUsage> findCardUsageByCardId(Long cardId, TransitionType transitionType);
	
	// Kartın en son kullanımını bulma
	@Query("select c from CardUsage c where c.cardId = ?1 order by c.createAt desc limit 1")
	Optional<CardUsage> findLastCardUsageByCardId(Long cardId); // En son kart kullanımını döndürür
}
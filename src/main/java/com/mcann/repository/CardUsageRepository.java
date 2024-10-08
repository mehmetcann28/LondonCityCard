package com.mcann.repository;

import com.mcann.entity.CardUsage;
import com.mcann.utility.enums.TransitionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CardUsageRepository extends JpaRepository<CardUsage, Long> {
//	CardUsage findFirstByCardIdAndTransitionTypeOrderByCreatedAtDesc(Long cardId, TransitionType transitionType);
	
	@Query("select c from CardUsage c where c.cardId = ?1 and c.transitionType = ?2 order by c.createAt desc limit 1")
	Optional<CardUsage> findCardUsageByCardId(Long cardId, TransitionType transitionType);
}
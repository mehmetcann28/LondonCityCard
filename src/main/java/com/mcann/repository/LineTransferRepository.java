package com.mcann.repository;

import com.mcann.entity.LineTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LineTransferRepository extends JpaRepository<LineTransfer, Long> {
	@Query("select count(lt) from LineTransfer lt where  lt.firstCardUsageId = ?1 or lt.nextCardUsageId = ?2")
	Long countByFirstCardUsageIdOrNextCardUsageId(Long firstCardUsageId, Long nextCardUsageId);
}
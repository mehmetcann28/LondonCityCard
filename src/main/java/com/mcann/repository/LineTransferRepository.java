package com.mcann.repository;

import com.mcann.entity.LineTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LineTransferRepository extends JpaRepository<LineTransfer, Long> {
	@Query("select count(lt) from LineTransfer lt where  lt.firstCardUsageId = ?1 or lt.nextCardUsageId = ?2")
	Long countByFirstCardUsageIdOrNextCardUsageId(Long firstCardUsageId, Long nextCardUsageId);

//	@Query("select lt from LineTransfer lt where lt.firstCardUsageId = ?1 or lt.nextCardUsageId = ?2 order by lt
//	.lineTransferDate desc")
//	Optional<LineTransfer> findTopByFirstCardUsageIdOrNextCardUsageIdOrderByLineTransferDateDesc(Long
//	firstCardUsageId, Long nextCardUsageId);
	
	//	@Query("select lt from LineTransfer lt where lt.firstCardUsageId = ?1 or lt.nextCardUsageId = ?2 order by lt
	//	.lineTransferDate desc limit 1")
//	Optional<LineTransfer> findTopByFirstCardUsageIdOrNextCardUsageIdOrderByLineTransferDateDesc(Long
//	firstCardUsageId, Long nextCardUsageId);
	@Query("select lt from LineTransfer lt where lt.firstCardUsageId = ?1 or lt.nextCardUsageId = ?2 order by lt" +
			".lineTransferDate desc limit 1")
	List<LineTransfer> findByFirstCardUsageIdOrNextCardUsageIdOrderByLineTransferDateDesc(Long firstCardUsageId,
	                                                                                      Long nextCardUsageId);
	@Query("SELECT lt FROM LineTransfer lt WHERE lt.firstCardUsageId = ?1 AND lt.nextCardUsageId = ?2")
	Optional<LineTransfer> findByFirstCardUsageIdAndNextCardUsageId(Long firstCardUsageId, Long nextCardUsageId);
}
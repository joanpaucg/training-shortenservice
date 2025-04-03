package com.jp.shortenservice.infrastructure.outbound.db.jpa

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface ShortenUrlStatsJpaRepository : JpaRepository<ShortenUrlStatsEntity, Long> {
    fun findByShortenUrlId(shortenUrlId: Long): ShortenUrlStatsEntity?
    @Modifying
    @Transactional
    @Query("UPDATE ShortenUrlStatsEntity sus SET sus.accessCount = sus.accessCount + 1 WHERE sus.shortenUrlId = :shortenUrlId")
    fun incrementAccessCount(shortenUrlId: Long)
}
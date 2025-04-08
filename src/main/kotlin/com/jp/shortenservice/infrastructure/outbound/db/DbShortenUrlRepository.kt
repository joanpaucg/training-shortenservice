package com.jp.shortenservice.infrastructure.outbound.db

import com.jp.shortenservice.domain.*
import com.jp.shortenservice.infrastructure.outbound.db.jpa.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class DbShortenUrlRepository (
    private val shortenUrlJpaRepository: ShortenUrlJpaRepository,
    private val shortCodeJpaRepository: ShortCodeJpaRepository,
    private val shortenUrlStatsJpaRepository: ShortenUrlStatsJpaRepository
) : ShortenUrlRepository {
    override fun save(shortCode: String, url: String): ShortenUrl {
        val shortCodeEntity=shortCodeJpaRepository.findByValue(shortCode)
        val shortenUrlEntity = ShortenUrlEntity(
            originalUrl = url,
                shortCode = shortCodeEntity!!,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )
        val savedEntity = shortenUrlJpaRepository.save(shortenUrlEntity)
        val statsEntity = ShortenUrlStatsEntity(
            shortenUrlId = savedEntity.id!!,
            accessCount = 0
        )
        shortenUrlStatsJpaRepository.save(statsEntity)
        return ShortenUrl(
                id = savedEntity.id!!,
                originalUrl = savedEntity.originalUrl,
                shortCode = ShortCode(savedEntity.shortCode.value),
                createdAt = savedEntity.createdAt,
                updatedAt = savedEntity.updatedAt
        )
    }

    override fun findByShortCode(shortCode: String): ShortenUrl? {
        val shortenUrlEntity = shortenUrlJpaRepository.findByShortCodeValue(shortCode)
        return shortenUrlEntity?.let {
            ShortenUrl(
                id = it.id!!,
                originalUrl = it.originalUrl,
                shortCode = ShortCode(it.shortCode.value),
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    override fun updateByShortCode(shortCode: String, originalUrl: String): ShortenUrl? {
        val shortenUrlEntity = shortenUrlJpaRepository.findByShortCodeValue(shortCode)
        return shortenUrlEntity?.let {
            val updatedEntity = shortenUrlEntity.copy(originalUrl = originalUrl, updatedAt = LocalDateTime.now())
            val savedEntity = shortenUrlJpaRepository.save(updatedEntity)
            ShortenUrl(
                id = savedEntity.id!!,
                originalUrl = savedEntity.originalUrl,
                shortCode = ShortCode(savedEntity.shortCode.value),
                createdAt = savedEntity.createdAt,
                updatedAt = savedEntity.updatedAt
            )
        }
    }
    @Transactional
    override fun deleteByShortCode(shortCode: String) {
        shortenUrlJpaRepository.findByShortCodeValue(shortCode)?.let {
            shortenUrlJpaRepository.delete(it)
            shortCodeJpaRepository.delete(it.shortCode)
        }
    }

    override fun findStatsByShortenUrlId(shortenUrlId: Long): Stats {
        val statsEntity = shortenUrlStatsJpaRepository.findByShortenUrlId(shortenUrlId) ?: shortenUrlStatsJpaRepository.save(
            ShortenUrlStatsEntity(
                shortenUrlId = shortenUrlId,
                accessCount = 0
            )
        )
        return Stats(
            accessCount = statsEntity.accessCount,
        )
    }

    override fun incrementAccessCount(shortenUrlId: Long) {
        val statsEntity = shortenUrlStatsJpaRepository.findByShortenUrlId(shortenUrlId)
        if (statsEntity != null) {
            shortenUrlStatsJpaRepository.incrementAccessCount(shortenUrlId)
        } else {
            shortenUrlStatsJpaRepository.save(
                ShortenUrlStatsEntity(
                    shortenUrlId = shortenUrlId,
                    accessCount = 1
                )
            )
        }
    }


}
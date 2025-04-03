package com.jp.shortenservice.infrastructure.outbound.db

import com.jp.shortenservice.domain.*
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortCodeEntity
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortCodeJpaRepository
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortenUrlEntity
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortenUrlJpaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class DbShortenUrlRepository (
    private val shortenUrlJpaRepository: ShortenUrlJpaRepository,
    private val shortCodeJpaRepository: ShortCodeJpaRepository
) : ShortenUrlRepository {
    override fun save(shortenUrl: UnsavedShortenUrl): SavedShortenUrl {
        val shortCodeEntity=shortCodeJpaRepository.findByValue(shortenUrl.shortCode.value)
        val shortenUrlEntity = ShortenUrlEntity(
            originalUrl = shortenUrl.originalUrl,
                shortCode = shortCodeEntity!!,
            createdAt = shortenUrl.createdAt,
            updatedAt = shortenUrl.updatedAt
        )
        val savedEntity = shortenUrlJpaRepository.save(shortenUrlEntity)
        return SavedShortenUrl(
                id = savedEntity.id!!,
                originalUrl = savedEntity.originalUrl,
                shortCode = ShortCode(savedEntity.shortCode.value),
                createdAt = savedEntity.createdAt,
                updatedAt = savedEntity.updatedAt
        )
    }

    override fun findByShortCode(shortCode: String): SavedShortenUrl? {
        val shortenUrlEntity = shortenUrlJpaRepository.findByShortCodeValue(shortCode)
        return shortenUrlEntity?.let {
            SavedShortenUrl(
                id = it.id!!,
                originalUrl = it.originalUrl,
                shortCode = ShortCode(it.shortCode.value),
                createdAt = it.createdAt,
                updatedAt = it.updatedAt
            )
        }
    }

    override fun updateByShortCode(shortCode: String, originalUrl: String): SavedShortenUrl? {
        val shortenUrlEntity = shortenUrlJpaRepository.findByShortCodeValue(shortCode)
        return shortenUrlEntity?.let {
            val updatedEntity = shortenUrlEntity.copy(originalUrl = originalUrl, updatedAt = LocalDateTime.now())
            val savedEntity = shortenUrlJpaRepository.save(updatedEntity)
            SavedShortenUrl(
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


}
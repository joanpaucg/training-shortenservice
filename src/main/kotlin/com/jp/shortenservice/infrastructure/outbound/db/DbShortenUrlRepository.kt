package com.jp.shortenservice.infrastructure.outbound.db

import com.jp.shortenservice.domain.SavedShortenUrl
import com.jp.shortenservice.domain.ShortenUrl
import com.jp.shortenservice.domain.ShortenUrlRepository
import com.jp.shortenservice.domain.UnsavedShortenUrl
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortenUrlEntity
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortenUrlJpaRepository
import org.springframework.stereotype.Repository

@Repository
class DbShortenUrlRepository (
    private val shortenUrlJpaRepository: ShortenUrlJpaRepository
) : ShortenUrlRepository {
    override fun save(shortenUrl: UnsavedShortenUrl): SavedShortenUrl {
        val shortenUrlEntity = ShortenUrlEntity(
            originalUrl = shortenUrl.originalUrl,
            shortCode = shortenUrl.shortCode,
            createdAt = shortenUrl.createdAt,
            updatedAt = shortenUrl.updatedAt
        )
        val savedEntity = shortenUrlJpaRepository.save(shortenUrlEntity)
        return SavedShortenUrl(
                id = savedEntity.id!!,
                originalUrl = savedEntity.originalUrl,
                shortCode = savedEntity.shortCode,
                createdAt = savedEntity.createdAt,
                updatedAt = savedEntity.updatedAt
        )
    }


}
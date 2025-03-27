package com.jp.shortenservice.infrastructure.outbound.db

import com.jp.shortenservice.domain.*
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortCodeEntity
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortCodeJpaRepository
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortenUrlEntity
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortenUrlJpaRepository
import org.springframework.stereotype.Repository

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



}
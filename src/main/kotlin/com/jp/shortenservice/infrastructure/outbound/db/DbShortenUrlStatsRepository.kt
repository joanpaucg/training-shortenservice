package com.jp.shortenservice.infrastructure.outbound.db

import com.jp.shortenservice.domain.Stats
import com.jp.shortenservice.domain.ShortenUrlStatsRepository
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortenUrlStatsEntity
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortenUrlStatsJpaRepository
import org.springframework.stereotype.Repository

@Repository
class DbShortenUrlStatsRepository(
        private val shortenUrlStatsJpaRepository: ShortenUrlStatsJpaRepository
) : ShortenUrlStatsRepository {
    override fun save(shortenUrlId: Long, accessCount: Long): Stats {
        val entity = shortenUrlStatsJpaRepository.findByShortenUrlId(shortenUrlId)
        return if (entity == null) {
            val newEntity = shortenUrlStatsJpaRepository.save(
                    ShortenUrlStatsEntity(
                            shortenUrlId = shortenUrlId,
                            accessCount = accessCount
                    )
            )
            Stats(
                    accessCount = newEntity.accessCount
            )
        } else {
            Stats(
                    accessCount = entity.accessCount
            )
        }
    }

    override fun findByShortenUrlId(shortenUrlId: Long): Stats? {
        val entity = shortenUrlStatsJpaRepository.findByShortenUrlId(shortenUrlId)
        return entity?.let {
            Stats(
                    accessCount = it.accessCount
            )
        }
    }

    override fun incrementAccessCount(shortenUrlId: Long) {
        shortenUrlStatsJpaRepository.incrementAccessCount(shortenUrlId)
    }
}
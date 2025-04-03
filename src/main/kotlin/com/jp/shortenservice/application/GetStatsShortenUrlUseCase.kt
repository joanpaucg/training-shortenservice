package com.jp.shortenservice.application

import com.jp.shortenservice.domain.ShortenUrlRepository
import com.jp.shortenservice.domain.ShortenUrlStats
import com.jp.shortenservice.domain.ShortenUrlStatsRepository
import org.springframework.stereotype.Service

@Service
class GetStatsShortenUrlUseCase(private val shortenUrlRepository: ShortenUrlRepository,private val shortenUrlStatsRepository: ShortenUrlStatsRepository) {
    // Implement your logic to get the stats of the shorten URL
    fun execute(shortCode: String): ShortenUrlStats? {
        val shortenUrl = shortenUrlRepository.findByShortCode(shortCode)
        return shortenUrl?.let {
            val stats = shortenUrlStatsRepository.findByShortenUrlId(shortenUrl.id)
            stats?.let {
                // Increment the access count
                return ShortenUrlStats(
                        id = shortenUrl.id,
                        shortCode = shortCode,
                        originalUrl = shortenUrl.originalUrl,
                        createdAt = shortenUrl.createdAt,
                        updatedAt = shortenUrl.updatedAt,
                        accessCount = stats.accessCount
                )
            }
        }



    }
}
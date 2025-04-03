package com.jp.shortenservice.application

import com.jp.shortenservice.domain.SavedShortenUrl
import com.jp.shortenservice.domain.ShortenUrlRepository
import com.jp.shortenservice.domain.ShortenUrlStats
import com.jp.shortenservice.domain.ShortenUrlStatsRepository
import org.springframework.stereotype.Service

@Service
class GetShortenUrlUseCase(private val shortenUrlRepository: ShortenUrlRepository,
                           private val shortenUrlStatsRepository: ShortenUrlStatsRepository) {
    fun execute(shortCode: String): SavedShortenUrl? {
        // Implement your logic to get the original URL from the short code
        // increment the access count
        val shortenUrl = shortenUrlRepository.findByShortCode(shortCode)
        shortenUrl?.let {
            val stats = shortenUrlStatsRepository.findByShortenUrlId(it.id)
            if (stats == null) {
                shortenUrlStatsRepository.save(shortenUrlId = it.id, accessCount = 1)
            } else {
                shortenUrlStatsRepository.incrementAccessCount(it.id)
            }
        }
        return shortenUrl
    }
}
package com.jp.shortenservice.application;

import com.jp.shortenservice.domain.ShortenUrlRepository
import org.springframework.stereotype.Service

@Service
class IncrementShortenUrlStatsUseCase(private val shortenUrlRepository: ShortenUrlRepository) {
    fun execute(shortenUrlId: Long) {
        shortenUrlRepository.incrementAccessCount(shortenUrlId)
    }
}

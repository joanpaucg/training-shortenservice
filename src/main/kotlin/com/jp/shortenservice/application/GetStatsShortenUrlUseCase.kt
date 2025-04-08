package com.jp.shortenservice.application

import com.jp.shortenservice.domain.ShortenUrlRepository
import org.springframework.stereotype.Service

@Service
class GetStatsShortenUrlUseCase(private val shortenUrlRepository: ShortenUrlRepository) {
    fun execute(shortCode: String): GetStatsShortenUrlResponse? {
        val shortenUrl = shortenUrlRepository.findByShortCode(shortCode) ?: return null
        val stats = shortenUrlRepository.findStatsByShortenUrlId(shortenUrl.id)
        return GetStatsShortenUrlResponse(
            shortenUrl = shortenUrl,
            stats = stats
        )
    }
}
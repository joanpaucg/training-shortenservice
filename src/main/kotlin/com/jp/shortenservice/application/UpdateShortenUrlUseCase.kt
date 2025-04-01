package com.jp.shortenservice.application

import com.jp.shortenservice.domain.SavedShortenUrl
import com.jp.shortenservice.domain.ShortenUrlRepository
import org.springframework.stereotype.Service

@Service
class UpdateShortenUrlUseCase(private val shortenUrlRepository: ShortenUrlRepository) {
    fun execute(shortCode: String, originalUrl: String): SavedShortenUrl? {
        // Implement your logic to update the original URL from the short code
        return shortenUrlRepository.updateByShortCode(shortCode, originalUrl)
    }
}
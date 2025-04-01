package com.jp.shortenservice.application

import com.jp.shortenservice.domain.SavedShortenUrl
import com.jp.shortenservice.domain.ShortenUrlRepository
import org.springframework.stereotype.Service

@Service
class GetShortenUrlUseCase(private val shortenUrlRepository: ShortenUrlRepository) {
    fun execute(shortCode: String): SavedShortenUrl? {
        // Implement your logic to get the original URL from the short code
        return shortenUrlRepository.findByShortCode(shortCode)
    }
}
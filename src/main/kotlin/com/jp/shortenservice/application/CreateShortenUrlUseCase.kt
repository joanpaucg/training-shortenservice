package com.jp.shortenservice.application

import com.jp.shortenservice.domain.SavedShortenUrl
import com.jp.shortenservice.domain.ShortenUrl
import com.jp.shortenservice.domain.ShortenUrlRepository
import com.jp.shortenservice.domain.UnsavedShortenUrl
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class CreateShortenUrlUseCase(val shortenUrlRepository: ShortenUrlRepository) {
    fun execute(url: String): SavedShortenUrl {

        val shortenUrl = UnsavedShortenUrl(
                originalUrl = url,
                shortCode = generateShortCode()
        )
        return shortenUrlRepository.save(shortenUrl)

    }
    private fun generateShortCode(): String {
        // Implement your logic to generate a random short code
        return UUID.randomUUID().toString().substring(0, 6)
    }
}
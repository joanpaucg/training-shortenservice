package com.jp.shortenservice.application

import com.jp.shortenservice.domain.*
import com.jp.shortenservice.domain.service.GenerateUniqueShortCodeService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class CreateShortenUrlUseCase(private val shortenUrlRepository: ShortenUrlRepository,private val shortCodeService: GenerateUniqueShortCodeService) {
    fun execute(url: String): ShortenUrl {
        return shortenUrlRepository.save(
            shortCode = generateShortCode().value,
            url = url
        )

    }
    private fun generateShortCode(): ShortCode {
        // Implement your logic to generate a random short code
        return shortCodeService.generateUniqueShortCode()
    }
}
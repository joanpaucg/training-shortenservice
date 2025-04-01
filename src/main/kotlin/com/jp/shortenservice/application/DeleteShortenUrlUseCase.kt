package com.jp.shortenservice.application

import com.jp.shortenservice.domain.ShortCodeRepository
import com.jp.shortenservice.domain.ShortenUrlRepository
import org.springframework.stereotype.Service

@Service
class DeleteShortenUrlUseCase(val shortenUrlRepository: ShortenUrlRepository) {
    fun execute(shortCode: String) {
        // Implement your logic to delete the shorten URL from the short code
        shortenUrlRepository.deleteByShortCode(shortCode)
    }
}
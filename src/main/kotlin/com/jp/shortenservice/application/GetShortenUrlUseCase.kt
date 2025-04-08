package com.jp.shortenservice.application

import com.jp.shortenservice.domain.ShortenUrl
import com.jp.shortenservice.domain.ShortenUrlRepository
import org.springframework.stereotype.Service

@Service
class GetShortenUrlUseCase(private val shortenUrlRepository: ShortenUrlRepository) {
    fun execute(shortCode: String): ShortenUrl? {
        val shortenUrl = shortenUrlRepository.findByShortCode(shortCode)?.also {
            shortenUrlRepository.incrementAccessCount(it.id)
        }
        return shortenUrl
    }
}
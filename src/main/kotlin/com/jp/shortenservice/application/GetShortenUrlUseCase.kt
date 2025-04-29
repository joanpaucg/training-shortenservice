package com.jp.shortenservice.application
import com.jp.shortenservice.domain.EventBus
import com.jp.shortenservice.domain.ShortenUrl
import com.jp.shortenservice.domain.ShortenUrlRepository
import com.jp.shortenservice.domain.ShortenUrlUsedEvent
import org.springframework.stereotype.Service

@Service
class GetShortenUrlUseCase(private val shortenUrlRepository: ShortenUrlRepository,
                           private val eventBus: EventBus) {
    fun execute(shortCode: String): ShortenUrl? {
        val shortenUrl = shortenUrlRepository.findByShortCode(shortCode)?.also {
            val event = ShortenUrlUsedEvent(shortenUrlId = it.id)
            eventBus.publish(event)
        }
        return shortenUrl
    }
}
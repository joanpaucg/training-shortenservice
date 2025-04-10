package com.jp.shortenservice.application
import com.jp.shortenservice.domain.ShortenUrl
import com.jp.shortenservice.domain.ShortenUrlRepository
import com.jp.shortenservice.domain.ShortenUrlUsedEvent
import com.jp.shortenservice.infrastructure.outbound.kafka.ShortenUrlUsedEventProducer
import org.springframework.stereotype.Service

@Service
class GetShortenUrlUseCase(private val shortenUrlRepository: ShortenUrlRepository,
                           private val shortenUrlUsedEventProducer: ShortenUrlUsedEventProducer) {
    fun execute(shortCode: String): ShortenUrl? {
        val shortenUrl = shortenUrlRepository.findByShortCode(shortCode)?.also {
            //shortenUrlRepository.incrementAccessCount(it.id)
            // Create and send the event
            val event = ShortenUrlUsedEvent(shortenUrlId = it.id)
            shortenUrlUsedEventProducer.sendShortenUrlUsedEvent(event)
        }
        return shortenUrl
    }
    fun incrementAccessCount(shortenUrlId: Long) {
        shortenUrlRepository.incrementAccessCount(shortenUrlId)
    }
}
package com.jp.shortenservice.infrastructure.outbound.kafka

import com.jp.shortenservice.domain.ShortenUrlUsedEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ShortenUrlUsedEventProducer(
        private val kafkaTemplate: KafkaTemplate<String, ShortenUrlUsedEvent>
) {
    private val topic = "shorten_url.used"

    fun sendShortenUrlUsedEvent(event: ShortenUrlUsedEvent) {
        kafkaTemplate.send(topic, event)
    }
}
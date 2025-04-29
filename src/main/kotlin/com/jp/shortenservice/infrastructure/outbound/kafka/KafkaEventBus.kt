package com.jp.shortenservice.infrastructure.outbound.kafka

import com.jp.shortenservice.domain.EventBus
import com.jp.shortenservice.domain.ShortenUrlUsedEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaEventBus (
        private val kafkaTemplate: KafkaTemplate<String, ShortenUrlUsedEvent>
) : EventBus {
    private val topic = "shorten_url.used"

    override fun publish(event: ShortenUrlUsedEvent) {
        kafkaTemplate.send(topic, event)
    }
}
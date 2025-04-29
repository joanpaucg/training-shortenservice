package com.jp.shortenservice.infrastructure.inbound.kafka
import com.jp.shortenservice.application.IncrementShortenUrlStatsUseCase
import com.jp.shortenservice.domain.ShortenUrlUsedEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
/*
Kafka Listener for ShortenUrlUsedEvent.
This class is responsible for consuming events from the Kafka topic shorten_url.used and incrementing the access count
of the corresponding ShortenUrl in the database.
Topic receives the following event format:
{
    "shortenUrlId": 1
}
*/
@Component
class ShortenUrlUsedEventConsumer(private val incrementShortenUrlStatsUseCase: IncrementShortenUrlStatsUseCase) {
    private val logger = LoggerFactory.getLogger(ShortenUrlUsedEventConsumer::class.java)

    @KafkaListener(topics = ["shorten_url.used"], groupId = "shorten-url-group")
    fun consume(event: ShortenUrlUsedEvent) {
        try {
            logger.info("Consumed event for shortenUrlId: ${event.shortenUrlId}")
            incrementShortenUrlStatsUseCase.execute(event.shortenUrlId)
        } catch (e: Exception) {
            logger.error("Error processing event: $event", e)
            // Optionally, handle retries or dead-letter queue logic here
        }
    }
}
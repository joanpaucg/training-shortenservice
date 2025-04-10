package com.jp.shortenservice.domain

import org.apache.kafka.shaded.com.google.protobuf.Timestamp
import java.time.Instant

data class ShortenUrlUsedEvent(
    val shortenUrlId: Long,
    val timestamp: Instant = Instant.now(),
) {
}
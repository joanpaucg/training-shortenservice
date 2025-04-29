package com.jp.shortenservice.domain

interface EventBus {
    fun publish(event: ShortenUrlUsedEvent)
}
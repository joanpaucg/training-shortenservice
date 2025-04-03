package com.jp.shortenservice.domain

import java.time.LocalDateTime

data class ShortenUrlStats(
        val id: Long,
        val originalUrl: String,
        val shortCode: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        val accessCount: Long
)
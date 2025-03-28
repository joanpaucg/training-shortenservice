package com.jp.shortenservice.domain

import java.time.LocalDateTime

 class SavedShortenUrl(
        val id: Long,
         originalUrl: String,
         shortCode: ShortCode,
         createdAt: LocalDateTime,
         updatedAt: LocalDateTime
): ShortenUrl(
        originalUrl = originalUrl,
        shortCode = shortCode,
        createdAt = createdAt,
        updatedAt = updatedAt
)
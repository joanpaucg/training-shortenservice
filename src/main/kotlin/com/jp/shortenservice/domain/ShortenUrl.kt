package com.jp.shortenservice.domain
import java.time.LocalDateTime

data class ShortenUrl(
         val id: Long,
         val originalUrl: String,
         val shortCode: ShortCode,
         val createdAt: LocalDateTime,
         val updatedAt: LocalDateTime,
)
package com.jp.shortenservice.domain
import java.time.LocalDateTime

sealed class ShortenUrl(
         val originalUrl: String,
         val shortCode: ShortCode,
         val createdAt: LocalDateTime,
         val updatedAt: LocalDateTime,

)
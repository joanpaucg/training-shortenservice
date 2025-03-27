package com.jp.shortenservice.domain

import java.time.LocalDateTime

class UnsavedShortenUrl(
        originalUrl:String,
        shortCode: ShortCode
): ShortenUrl(
        originalUrl = originalUrl,
        shortCode = shortCode,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
)


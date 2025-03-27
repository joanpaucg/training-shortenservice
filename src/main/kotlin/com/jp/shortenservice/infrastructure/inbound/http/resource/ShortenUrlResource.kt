package com.jp.shortenservice.infrastructure.inbound.http.resource

import com.jp.shortenservice.domain.SavedShortenUrl

data class ShortenUrlResource(
    val id: String,
    val url: String,
    val shortCode: String,
    val createdAt: String,
    val updatedAt: String
) {

    companion object {
        fun SavedShortenUrl.toResource(): ShortenUrlResource =
            ShortenUrlResource(
                id = id.toString(),
                url = originalUrl,
                shortCode = shortCode,
                createdAt = createdAt.toString(),
                updatedAt = updatedAt.toString()
            )
        fun savedShortenUrlToResource(savedShortenUrl: SavedShortenUrl): ShortenUrlResource {
            return ShortenUrlResource(
                id = savedShortenUrl.id.toString(),
                url = savedShortenUrl.originalUrl,
                shortCode = savedShortenUrl.shortCode,
                createdAt = savedShortenUrl.createdAt.toString(),
                updatedAt = savedShortenUrl.updatedAt.toString()
            )
        }

    }

}
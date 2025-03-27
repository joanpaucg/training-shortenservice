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
                shortCode = shortCode.value,
                createdAt = createdAt.toString(),
                updatedAt = updatedAt.toString()
            )

    }

}
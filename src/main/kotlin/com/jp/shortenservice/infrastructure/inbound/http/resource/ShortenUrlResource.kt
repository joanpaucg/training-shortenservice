package com.jp.shortenservice.infrastructure.inbound.http.resource

import com.jp.shortenservice.domain.ShortenUrl

data class ShortenUrlResource(
    val id: String,
    val url: String,
    val shortCode: String,
    val createdAt: String,
    val updatedAt: String
) {

    companion object {
        fun ShortenUrl.toResource(): ShortenUrlResource =
            ShortenUrlResource(
                id = id.toString(),
                url = originalUrl,
                shortCode = shortCode.value,
                createdAt = createdAt.toString(),
                updatedAt = updatedAt.toString()
            )

    }

}
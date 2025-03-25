package com.jp.shortenservice.infrastructure.inbound.http.resource

data class ShortenUrlResource(
    val id: String,
    val url: String,
    val shortCode: String,
    val createdAt: String,
    val updatedAt: String
)
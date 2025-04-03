package com.jp.shortenservice.infrastructure.inbound.http.resource

data class StatsShortenUrlResource(
        val id:String,
        val originalUrl: String,
        val shortCode: String,
        val createdAt: String,
        val updatedAt: String,
        val accessCount: Long
) {
}
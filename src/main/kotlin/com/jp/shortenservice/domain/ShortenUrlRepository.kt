package com.jp.shortenservice.domain

interface ShortenUrlRepository {
    fun save(shortCode: String,url: String): ShortenUrl
    fun findByShortCode(shortCode: String): ShortenUrl?
    fun updateByShortCode(shortCode: String, originalUrl: String): ShortenUrl?
    fun deleteByShortCode(shortCode: String)
    fun findStatsByShortenUrlId(shortenUrlId: Long): Stats
    fun incrementAccessCount(shortenUrlId: Long)
}
package com.jp.shortenservice.domain

interface ShortenUrlRepository {
    fun save(shortenUrl: UnsavedShortenUrl): SavedShortenUrl
    fun findByShortCode(shortCode: String): SavedShortenUrl?
    fun updateByShortCode(shortCode: String, originalUrl: String): SavedShortenUrl?
    fun deleteByShortCode(shortCode: String)
}
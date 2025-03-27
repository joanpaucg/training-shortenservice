package com.jp.shortenservice.domain

interface ShortenUrlRepository {
    fun save(shortenUrl: UnsavedShortenUrl): SavedShortenUrl
}
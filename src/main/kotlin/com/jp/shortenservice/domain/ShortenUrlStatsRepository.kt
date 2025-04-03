package com.jp.shortenservice.domain

interface ShortenUrlStatsRepository {
    fun save(shortenUrlId: Long,accessCount: Long): Stats
    fun findByShortenUrlId(shortenUrlId: Long): Stats?
    fun incrementAccessCount(shortenUrlId: Long)
}
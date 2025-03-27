package com.jp.shortenservice.domain

interface ShortCodeRepository {
    fun save(shortCode: ShortCode): ShortCode
    fun findByValue(value: String): ShortCode?
}
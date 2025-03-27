package com.jp.shortenservice.infrastructure.outbound.db.jpa

import java.time.LocalDateTime
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "shorten_url")
class ShortenUrlEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val originalUrl: String,
        val shortCode: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)
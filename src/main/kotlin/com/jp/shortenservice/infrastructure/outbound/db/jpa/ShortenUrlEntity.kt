package com.jp.shortenservice.infrastructure.outbound.db.jpa

import java.time.LocalDateTime
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity(name = "shorten_url")
data class ShortenUrlEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val originalUrl: String,
        @OneToOne
        @JoinColumn(name = "short_code_id", referencedColumnName = "id")
        val shortCode: ShortCodeEntity,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
)
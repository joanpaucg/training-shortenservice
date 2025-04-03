package com.jp.shortenservice.infrastructure.outbound.db.jpa

import jakarta.persistence.*

@Entity
@Table(name = "shorten_url_stats")
data class ShortenUrlStatsEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?= null,
    @Column(name = "shorten_url_id")
    val shortenUrlId: Long,
    @Column(name = "access_count")
    val accessCount: Long
)


package com.jp.shortenservice.infrastructure.outbound.db.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface ShortCodeJpaRepository : JpaRepository<ShortCodeEntity, Long> {
    fun findByValue(value: String): ShortCodeEntity?
}
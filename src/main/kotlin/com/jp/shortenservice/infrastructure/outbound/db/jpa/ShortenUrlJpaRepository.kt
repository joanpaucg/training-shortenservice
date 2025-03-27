package com.jp.shortenservice.infrastructure.outbound.db.jpa

import com.jp.shortenservice.domain.ShortenUrl
import org.springframework.data.jpa.repository.JpaRepository

interface ShortenUrlJpaRepository : JpaRepository<ShortenUrlEntity, Long> {

}
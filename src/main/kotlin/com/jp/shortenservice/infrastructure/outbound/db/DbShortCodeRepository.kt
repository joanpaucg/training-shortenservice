package com.jp.shortenservice.infrastructure.outbound.db

import com.jp.shortenservice.domain.ShortCode
import com.jp.shortenservice.domain.ShortCodeRepository
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortCodeEntity
import com.jp.shortenservice.infrastructure.outbound.db.jpa.ShortCodeJpaRepository
import org.springframework.stereotype.Repository

@Repository
class DbShortCodeRepository(private val shortCodeJpaRepository: ShortCodeJpaRepository) : ShortCodeRepository {
    override fun save(shortCode: ShortCode): ShortCode {
        val shortCodeEntity = ShortCodeEntity(
                value = shortCode.value
        )
        val savedEntity = shortCodeJpaRepository.save(shortCodeEntity)
        return ShortCode(
                value = savedEntity.value
        )
    }

    override fun findByValue(value: String): ShortCode? {
        val shortCodeEntity = shortCodeJpaRepository.findByValue(value)
        return shortCodeEntity?.let {
            ShortCode(
                    value = it.value
            )
        }
    }


}
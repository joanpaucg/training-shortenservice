package com.jp.shortenservice.domain.service

import com.jp.shortenservice.domain.ShortCode
import com.jp.shortenservice.domain.ShortCodeRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GenerateUniqueShortCodeService(private val shortCodeRepository: ShortCodeRepository) {
    // repositori short codes
    // taula short codes unics
    // servei ha de generar random strings
    // Els ha de provar de guardar amb el repositori
    // Pero si falla per duplicitat
    // ha de tornar a generar un altre random string
    // El use case ha de cridar aquest servei
    // ShortenUrl deixa de tenir el string i passa a tenir el ShortCode


    fun generateUniqueShortCode(): ShortCode {
        var shortCodeValue = generateRandomString()
        while (shortCodeRepository.findByValue(shortCodeValue) != null) {
            shortCodeValue = generateRandomString()
        }
        return shortCodeRepository.save(ShortCode(shortCodeValue))
    }
    private fun generateRandomString(length: Int = 6): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
                .map { chars.random() }
                .joinToString("")
    }
}
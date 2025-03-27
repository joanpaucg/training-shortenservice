package com.jp.shortenservice.infrastructure.inbound.http

import com.jp.shortenservice.application.CreateShortenUrlUseCase
import com.jp.shortenservice.domain.SavedShortenUrl
import com.jp.shortenservice.domain.ShortenUrl
import com.jp.shortenservice.infrastructure.inbound.http.resource.CreateShortenUrlResource
import com.jp.shortenservice.infrastructure.inbound.http.resource.ShortenUrlResource
import com.jp.shortenservice.infrastructure.inbound.http.resource.ShortenUrlResource.Companion.toResource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RequestMapping("/shorten")
@RestController
class ShortenUrlController(private val createShortenUrlUseCase: CreateShortenUrlUseCase) {

    @PostMapping
    fun createShortenUrl(@RequestBody body:CreateShortenUrlResource):ResponseEntity<ShortenUrlResource> {
        val savedShortenUrl = createShortenUrlUseCase.execute(body.url)
        val shortenUrlResource = savedShortenUrl.toResource()
        return ResponseEntity.status(HttpStatus.CREATED).body(shortenUrlResource)
    }
}



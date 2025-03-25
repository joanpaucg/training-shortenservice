package com.jp.shortenservice.infrastructure.inbound.http

import com.jp.shortenservice.infrastructure.inbound.http.resource.CreateShortenUrlResource
import com.jp.shortenservice.infrastructure.inbound.http.resource.ShortenUrlResource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@RequestMapping("/shorten")
@RestController
class ShortenUrlController {

    @PostMapping
    fun createShortenUrl(@RequestBody body:CreateShortenUrlResource):ResponseEntity<ShortenUrlResource> {
        val shortenUrlResource = ShortenUrlResource(
            id = "1",
            url = body.url,
            shortCode = "shortCode",
            createdAt = "2021-01-01T00:00:00Z",
            updatedAt = "2021-01-01T00:00:00Z"
        )
        return ResponseEntity.status(HttpStatus.CREATED).body(shortenUrlResource)
    }
}
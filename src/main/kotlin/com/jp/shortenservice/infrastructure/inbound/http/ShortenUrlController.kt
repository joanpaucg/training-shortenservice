package com.jp.shortenservice.infrastructure.inbound.http

import com.jp.shortenservice.application.*
import com.jp.shortenservice.domain.SavedShortenUrl
import com.jp.shortenservice.domain.ShortenUrl
import com.jp.shortenservice.infrastructure.inbound.http.resource.CreateShortenUrlResource
import com.jp.shortenservice.infrastructure.inbound.http.resource.ShortenUrlResource
import com.jp.shortenservice.infrastructure.inbound.http.resource.ShortenUrlResource.Companion.toResource
import com.jp.shortenservice.infrastructure.inbound.http.resource.StatsShortenUrlResource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
@RequestMapping("/shorten")
@RestController
class ShortenUrlController(private val createShortenUrlUseCase: CreateShortenUrlUseCase,
                           private val getShortenUrlUseCase: GetShortenUrlUseCase,
                           private val updateShortenUrlUseCase: UpdateShortenUrlUseCase,
                           private val deleteShortenUrlUseCase: DeleteShortenUrlUseCase,
                            private val getStatsShortenUrlUseCase: GetStatsShortenUrlUseCase
        ) {

    @PostMapping
    fun createShortenUrl(@RequestBody body:CreateShortenUrlResource):ResponseEntity<ShortenUrlResource> {
        val savedShortenUrl = createShortenUrlUseCase.execute(body.url)
        val shortenUrlResource = savedShortenUrl.toResource()
        return ResponseEntity.status(HttpStatus.CREATED).body(shortenUrlResource)
    }
    @GetMapping("/{shortCode}")
    fun getShortenUrl(@PathVariable("shortCode") shortCode:String):ResponseEntity<ShortenUrlResource> {
        val savedShortenUrl = getShortenUrlUseCase.execute(shortCode)
        return savedShortenUrl?.let {
            ResponseEntity.ok(it.toResource())
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }
    @PutMapping("/{shortCode}")
    fun updateShortenUrl(@PathVariable("shortCode") shortCode:String,@RequestBody body:CreateShortenUrlResource):ResponseEntity<ShortenUrlResource> {
        val savedShortenUrl = updateShortenUrlUseCase.execute(shortCode=shortCode,originalUrl = body.url)
        return savedShortenUrl?.let {
            ResponseEntity.ok(it.toResource())
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{shortCode}")
    fun deleteShortenUrl(@PathVariable("shortCode") shortCode:String){
        // Implement your logic to delete the shorten URL from the short code
        deleteShortenUrlUseCase.execute(shortCode)

    }
    /**/
    @GetMapping("/{shortCode}/stats")
    fun getStatsShortenUrl(@PathVariable("shortCode") shortCode:String):ResponseEntity<StatsShortenUrlResource> {
        // Implement your logic to get the stats of the shorten URL
        val statsShortenUrl = getStatsShortenUrlUseCase.execute(shortCode)
        statsShortenUrl?.let {
            return ResponseEntity.ok(
                StatsShortenUrlResource(
                        id = statsShortenUrl.id.toString(),
                        shortCode = statsShortenUrl.shortCode,
                        originalUrl = statsShortenUrl.originalUrl,
                        createdAt = statsShortenUrl.createdAt.toString(),
                        updatedAt = statsShortenUrl.updatedAt.toString(),
                        accessCount = statsShortenUrl.accessCount
                )
            )
        } ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).build()

    }
}



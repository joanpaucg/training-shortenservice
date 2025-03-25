package com.jp.shortenservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.jp.shortenservice"])
class ShortenServiceApplication

fun main(args: Array<String>) {
	runApplication<ShortenServiceApplication>(*args)
}

package com.jp.shortenservice.application

import com.jp.shortenservice.domain.ShortenUrl
import com.jp.shortenservice.domain.Stats

data class GetStatsShortenUrlResponse(
        val shortenUrl: ShortenUrl,
        val stats: Stats,
)


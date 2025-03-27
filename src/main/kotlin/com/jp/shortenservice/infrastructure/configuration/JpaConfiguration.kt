package com.jp.shortenservice.infrastructure.configuration

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["com.jp.shortenservice.infrastructure.outbound.db.jpa"])
@EntityScan(basePackages = ["com.jp.shortenservice.infrastructure.outbound.db.jpa"])
class JpaConfiguration
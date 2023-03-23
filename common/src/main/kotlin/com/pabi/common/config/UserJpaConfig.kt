package com.pabi.common.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["com.pabi.user.domain"])
@EnableJpaRepositories(basePackages = ["com.pabi.user.infrastructure"])
class UserJpaConfig

package com.pabi.common.config

import com.pabi.common.jwt.JwtFilter
import com.pabi.common.jwt.JwtUserRepository
import com.pabi.common.jwt.TokenProvider
import com.pabi.common.redis.RedisRepository
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtSecurityConfig(
    private val tokenProvider: TokenProvider,
    private val jwtUserRepository: JwtUserRepository,
    private val redisRepository: RedisRepository,
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {

    override fun configure(http: HttpSecurity) {
        val customFilter = JwtFilter(tokenProvider, jwtUserRepository, redisRepository)
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}

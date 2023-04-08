package com.pabi.user.domain.service

import com.pabi.common.exception.InvalidTokenException
import com.pabi.common.jwt.TokenProvider
import com.pabi.common.redis.RedisRepository
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class LogOutUserService(
    private val tokenProvider: TokenProvider,
    private val redisRepository: RedisRepository,
) {
    fun logOutUser(email: String, requestAccessToken: String) {
        val accessToken = tokenProvider.resolveToken(requestAccessToken)
            ?: throw InvalidTokenException()

        redisRepository.delValue(redisRepository.REFRESH_PREFIX + email)

        redisRepository.setValue(
            accessToken,
            redisRepository.ACCESS_BLACK + email,
            tokenProvider.getExpirationFromToken(accessToken).time,
            TimeUnit.MILLISECONDS
        )
    }
}

package com.pabi.user.domain.service

import com.pabi.common.exception.InvalidTokenException
import com.pabi.common.jwt.TokenProvider
import com.pabi.common.redis.RedisRepository
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class SignOutUserService(
    private val tokenProvider: TokenProvider,
    private val redisRepository: RedisRepository,
) {
    fun signOutUser(
        accessToken: String
    ) {
        val token = tokenProvider.resolveToken(accessToken)
            ?: throw InvalidTokenException()

        if (!tokenProvider.validateToken(token)) {
            throw InvalidTokenException()
        }
        val tokenEmail = tokenProvider.getEmailFromToken(token)


        redisRepository.delValue(redisRepository.REFRESH_PREFIX + tokenEmail)

        redisRepository.setValue(
            redisRepository.ACCESS_BLACK + tokenEmail,
            token,
            tokenProvider.getExpirationFromToken(token).time,
            TimeUnit.MILLISECONDS
        )
    }
}
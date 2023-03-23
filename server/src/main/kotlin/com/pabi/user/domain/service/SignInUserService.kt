package com.pabi.user.domain.service

import com.pabi.common.exception.NotFoundUserEmailException
import com.pabi.common.jwt.TokenProvider
import com.pabi.common.redis.RedisRepository
import com.pabi.user.domain.dto.SignInUserDto.SignInUserCommand
import com.pabi.user.domain.dto.SignInUserDto.SignInUserInfo
import com.pabi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class SignInUserService(
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider,
    private val redisRepository: RedisRepository,
) {
    fun signInUser(request: SignInUserCommand): SignInUserInfo {
        with(request) {
            val user = userRepository.findByEmail(email) ?: throw NotFoundUserEmailException()
            user.signIn(password)

            val token = tokenProvider.createTokens(email, user.roles)

            redisRepository.setValue(
                redisRepository.REFRESH_PREFIX + email,
                token.refreshToken,
                tokenProvider.refreshTokenValidityInMilliseconds,
                TimeUnit.MILLISECONDS
            )
            return SignInUserInfo(token)
        }
    }
}

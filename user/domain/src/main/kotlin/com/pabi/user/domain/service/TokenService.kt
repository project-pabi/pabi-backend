package com.pabi.user.domain.service

import com.pabi.common.exception.InvalidTokenException
import com.pabi.common.jwt.TokenProvider
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class TokenService(
    private val tokenProvider: TokenProvider,
) {

    fun tokenReissue(accessToken: String, refreshToken: String): String {
        val access = resolveToken(accessToken)
        val refresh = resolveToken(refreshToken)
        return tokenProvider.tokenReissue(access, refresh)
    }

    fun resolveToken(token: String): String {

        if (!StringUtils.hasText(token)) {
            throw InvalidTokenException()
        }

        if (!token.startsWith("Bearer ")) {
            throw InvalidTokenException()
        }

        return token.substring(7)
    }
}
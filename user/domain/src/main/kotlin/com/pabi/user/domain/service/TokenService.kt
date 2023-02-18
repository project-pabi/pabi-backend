package com.pabi.user.domain.service

import com.pabi.common.jwt.TokenProvider
import com.pabi.common.response.Token
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val tokenProvider: TokenProvider,
) {

    fun tokenReissue(accessToken: String, refreshToken: String): Token {
        val access = tokenProvider.resolveToken(accessToken)
        val refresh = tokenProvider.resolveToken(refreshToken)
        return tokenProvider.tokenReissue(access, refresh)
    }
}
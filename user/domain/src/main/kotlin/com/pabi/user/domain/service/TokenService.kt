package com.pabi.user.domain.service

import com.pabi.common.exception.InvalidTokenException
import com.pabi.common.jwt.TokenProvider
import com.pabi.user.domain.dto.TokenDto
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val tokenProvider: TokenProvider,
) {

    fun tokenReissue(accessToken: String, refreshToken: String): TokenDto.TokenReissueInfo {
        val access = tokenProvider.resolveToken(accessToken)
        val refresh = tokenProvider.resolveToken(refreshToken)

        if (access == null || refresh == null) {
            throw InvalidTokenException()
        }

        return TokenDto.TokenReissueInfo(tokenProvider.tokenReissue(access, refresh))
    }
}
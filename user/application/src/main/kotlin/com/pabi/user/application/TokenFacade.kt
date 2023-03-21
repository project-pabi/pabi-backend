package com.pabi.user.application

import com.pabi.user.domain.service.TokenService
import org.springframework.stereotype.Service

@Service
class TokenFacade(
    val tokenService: TokenService
) {

    fun tokenReissue(
        accessToken: String,
        refreshToken: String,
    ) = tokenService.tokenReissue(accessToken, refreshToken)
}
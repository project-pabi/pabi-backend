package com.pabi.user.presentation

import com.pabi.common.jwt.JwtFilter.Companion.AUTHORIZATION_HEADER
import com.pabi.common.jwt.JwtFilter.Companion.REFRESH_TOKEN_HEADER
import com.pabi.common.response.CommonResponse
import com.pabi.user.application.TokenFacade
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/token")
class TokenReissueController(
    val tokenFacade: TokenFacade,
) {

    @PostMapping
    fun tokenReissue(
        @RequestHeader(AUTHORIZATION_HEADER) accessToken: String,
        @RequestHeader(REFRESH_TOKEN_HEADER) refreshToken: String,
    ): CommonResponse<String> {
        val token = tokenFacade.tokenReissue(accessToken, refreshToken)
        return CommonResponse.success(token, "토큰이 재발급 되었습니다.");
    }
}
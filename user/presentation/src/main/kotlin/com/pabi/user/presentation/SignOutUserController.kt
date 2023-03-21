package com.pabi.user.presentation

import com.pabi.common.jwt.JwtFilter
import com.pabi.common.response.CommonResponse
import com.pabi.user.application.SignOutUserFacade
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
class SignOutUserController(
    private val signOutUserFacade: SignOutUserFacade,
) {

    @PostMapping("/sign-out")
    @ApiOperation(value = "유저 로그아웃")
    fun signOutUser(
        @RequestHeader(JwtFilter.AUTHORIZATION_HEADER) accessToken: String,
    ): CommonResponse<Nothing> {
        signOutUserFacade.signOutUser(accessToken);
        return CommonResponse.success("로그아웃 되었습니다.");
    }
}
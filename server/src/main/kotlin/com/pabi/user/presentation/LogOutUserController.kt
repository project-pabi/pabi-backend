package com.pabi.user.presentation

import com.pabi.common.jwt.JwtFilter
import com.pabi.common.response.CommonResponse
import com.pabi.user.application.LogOutUserFacade
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1/user")
class LogOutUserController(
    private val logOutUserFacade: LogOutUserFacade,
) {

    @PostMapping("/log-out")
    @ApiOperation(value = "유저 로그아웃")
    fun logOutUser(
        principal: Principal,
        @RequestHeader(JwtFilter.AUTHORIZATION_HEADER) accessToken: String,
    ): CommonResponse<Nothing> {
        logOutUserFacade.logOutUser(principal.name, accessToken)
        return CommonResponse.success("로그아웃 되었습니다.")
    }
}

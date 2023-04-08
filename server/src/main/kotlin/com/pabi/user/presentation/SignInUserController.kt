package com.pabi.user.presentation

import com.pabi.common.response.CommonResponse
import com.pabi.user.application.SignInUserFacade
import com.pabi.user.presentation.SignInUserDto.SignInUserRequest
import com.pabi.user.presentation.SignInUserDto.SignInUserResponse
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class SignInUserController(
    private val signInUserFacade: SignInUserFacade,
) {

    @PostMapping("/sign-in")
    @ApiOperation(value = "유저 로그인")
    fun signInUser(
        @Valid @RequestBody
        request: SignInUserRequest,
    ):
        CommonResponse<SignInUserResponse> {
        val command = request.toCommand()
        val info = signInUserFacade.signInUser(command)
        val response = SignInUserResponse(info)
        return CommonResponse.success(response)
    }
}

package com.pabi.user.presentation

import com.pabi.common.response.CommonResponse
import com.pabi.user.application.SignUpUserFacade
import com.pabi.user.presentation.SignUpUserDto.SignUpUserRequest
import com.pabi.user.presentation.SignUpUserDto.SignUpUserResponse
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/api/v1/user")
@RestController
class SignUpUserController(
    private val signUpUserFacade: SignUpUserFacade,
) {

    @PostMapping
    @ApiOperation(value = "유저 생성")
    fun signUp(@Valid @RequestBody request: SignUpUserRequest): CommonResponse<SignUpUserResponse> {
        val command = request.toCommand()
        val info = signUpUserFacade.signUp(command)
        val response = SignUpUserResponse(info)
        return CommonResponse.success(response)
    }
}
package com.pabi.user.presentation

import com.pabi.common.response.CommonResponse
import com.pabi.user.application.FindUserFacade
import com.pabi.user.application.SignInUserFacade
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/user")
class findUserController(
    private val findUserFacade: FindUserFacade,
) {

    @GetMapping("/user")
    @ApiOperation(value = "유저 로그인")
    fun signInUser(@Valid @RequestBody request: SignInUserRequest):
            CommonResponse<SignInUserResponse> {
        val command = request.toCommand()
        val info = signInUserFacade.signInUser(command)
        val response = SignInUserDto.SignInUserResponse(info)
        return CommonResponse.success(response)
    }

    @GetMapping("/user")
    @ApiOperation(value = "유저 조회")
    fun findUser(@Valid @RequestBody request: findUserDto.FindUserRequest): CommonResponse<findUserDto.FindUserResponse> {
        val command = request.toCommand()
        val info = findUserFacade.FindUser(command)
        val response = findUserDto.FindUserResponse(info)
        return CommonResponse.success(response)
    }
}
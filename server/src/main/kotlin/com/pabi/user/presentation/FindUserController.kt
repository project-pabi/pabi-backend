package com.pabi.user.presentation

import FindUserDto
import com.pabi.common.response.CommonResponse
import com.pabi.user.application.FindUserFacade
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/user")
class FindUserController(
    private val findUserFacade: FindUserFacade,
) {
    @GetMapping("/profile")
    @ApiOperation(value = "유저 조회")
    fun findUser(@Valid @RequestBody request: FindUserDto.FindUserRequest): CommonResponse<FindUserDto.FindUserResponse> {
        val command = request.toCommand()
        val info = findUserFacade.findUser(command)
        val response = FindUserDto.FindUserResponse(info)
        return CommonResponse.success(response)
    }
}

package com.pabi.user.presentation
import com.pabi.common.response.CommonResponse
import com.pabi.user.application.FindUserFacade
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class FindUserController(
    private val findUserFacade: FindUserFacade,
) {
    @GetMapping("/{userId}")
    @ApiOperation(value = "유저 조회")
    fun findUser(@PathVariable("userId") userId: Long): CommonResponse<FindUserDto.FindUserResponse> {
        val info = findUserFacade.findUser(userId)
        val response = FindUserDto.FindUserResponse(info)
        return CommonResponse.success(response)
    }
}

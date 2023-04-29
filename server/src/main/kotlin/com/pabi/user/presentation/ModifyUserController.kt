package com.pabi.user.presentation

import com.pabi.common.response.CommonResponse
import com.pabi.user.application.ModifyUserFacade
import com.pabi.user.presentation.ModifyUserDto.ModifyUserRequest
import com.pabi.user.presentation.ModifyUserDto.ModifyUserResponse
import io.swagger.annotations.ApiOperation
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/user")
class ModifyUserController(
    val modifyUserFacade: ModifyUserFacade,
) {

    @PatchMapping
    @ApiOperation(value = "유저 수정")
    @PreAuthorize("hasRole('USER')")
    fun modifyUser(
        principal: Principal,
        @Valid @RequestBody
        request: ModifyUserRequest,
    ): CommonResponse<ModifyUserResponse> {
        val command = request.toCommand()
        val info = modifyUserFacade.modifyUser(principal.name, command)
        val response = ModifyUserResponse(info)
        return CommonResponse.success(response)
    }
}

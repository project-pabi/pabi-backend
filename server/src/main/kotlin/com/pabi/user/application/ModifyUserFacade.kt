package com.pabi.user.application

import com.pabi.user.domain.dto.ModifyUserDto
import com.pabi.user.domain.service.ModifyUserService
import org.springframework.stereotype.Service

@Service
class ModifyUserFacade(
    val modifyUserService: ModifyUserService,
) {

    fun modifyUser(
        email: String,
        request: ModifyUserDto.ModifyUserCommand,
    ) = modifyUserService.modifyUser(email, request)
}

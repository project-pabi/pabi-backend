package com.pabi.user.application

import com.pabi.user.domain.dto.FindUserDto
import com.pabi.user.domain.service.FindUserService
import org.springframework.stereotype.Service

@Service
class FindUserFacade(
    private val FindUserService: FindUserService,
) {

    fun findUser(command: FindUserDto.FindUserCommand) = FindUserService.findUser(command)
}

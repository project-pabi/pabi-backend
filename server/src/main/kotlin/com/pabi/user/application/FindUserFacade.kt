package com.pabi.user.application

import com.pabi.user.domain.dto.FindUserDto
import com.pabi.user.domain.service.FindUserService
import org.springframework.stereotype.Service

@Service
class FindUserFacade(private val findUserService: FindUserService) {
    fun findUser(command: FindUserDto.FindUserCommand) = findUserService.findUser(command)
}

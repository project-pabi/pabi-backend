package com.pabi.user.application

import com.pabi.user.domain.dto.SignInUserDto.SignInUserCommand
import com.pabi.user.domain.service.SignInUserService
import org.springframework.stereotype.Service

@Service
class SignInUserFacade(
    private val signInUserService: SignInUserService,
) {

    fun signInUser(request: SignInUserCommand) = signInUserService.signInUser(request)
}

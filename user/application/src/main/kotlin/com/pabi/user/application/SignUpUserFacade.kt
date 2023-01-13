package com.pabi.user.application

import com.pabi.user.domain.dto.SignUpUserDto.SignUpUserCommand
import com.pabi.user.domain.service.SignUpUserService
import org.springframework.stereotype.Service

@Service
class SignUpUserFacade(
    private val signUpUserService: SignUpUserService,
) {

    fun signUp(request: SignUpUserCommand) = signUpUserService.signUpUser(request)
}
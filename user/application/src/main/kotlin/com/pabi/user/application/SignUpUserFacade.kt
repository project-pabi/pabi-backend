package com.pabi.user.application

import com.pabi.user.application.SignUpUserDto.SignUpUserCommand
import com.pabi.user.application.SignUpUserDto.SignUpUserInfo
import com.pabi.user.domain.service.UserInputService
import org.springframework.stereotype.Service

@Service
class SignUpUserFacade(
    private val userInputService: UserInputService,
) {

    fun signUp(request: SignUpUserCommand): SignUpUserInfo {
        val user = userInputService.inputUser(request.toEntity())
        return SignUpUserInfo(user)
    }
}
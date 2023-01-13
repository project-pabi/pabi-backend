package com.pabi.user.domain.service

import com.pabi.common.exception.InvalidPasswordException
import com.pabi.common.exception.NotFoundUserEmailException
import com.pabi.user.domain.dto.SignInUserDto.SignInUserCommand
import com.pabi.user.domain.dto.SignInUserDto.SignInUserInfo
import com.pabi.user.domain.dto.common.Token
import com.pabi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SignInUserService(
    private val userRepository: UserRepository,
) {

    fun signInUser(request: SignInUserCommand): SignInUserInfo {
        with(request) {
            val user = userRepository.findByEmail(email) ?: throw NotFoundUserEmailException()

            if (user.password != password) {
                throw InvalidPasswordException()
            }

            return SignInUserInfo(Token("", "", "", 0, Date()))
        }
    }
}
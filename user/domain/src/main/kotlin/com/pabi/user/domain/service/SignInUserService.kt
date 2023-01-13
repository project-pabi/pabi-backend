package com.pabi.user.domain.service

import com.pabi.common.exception.InvalidPasswordException
import com.pabi.common.exception.NotFoundUserEmailException
import com.pabi.common.exception.WithdrawalUserException
import com.pabi.common.jwt.TokenProvider
import com.pabi.user.domain.dto.SignInUserDto.SignInUserCommand
import com.pabi.user.domain.dto.SignInUserDto.SignInUserInfo
import com.pabi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class SignInUserService(
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider,
) {

    fun signInUser(request: SignInUserCommand): SignInUserInfo {
        with(request) {
            val user = userRepository.findByEmail(email) ?: throw NotFoundUserEmailException()

            if (user.password != password) {
                throw InvalidPasswordException()
            }

            if (user.withdrawal) {
                throw WithdrawalUserException()
            }

            val token = tokenProvider.createTokens(email, user.roles)

            return SignInUserInfo(token)
        }
    }
}
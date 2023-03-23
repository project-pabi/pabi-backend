package com.pabi.user.domain.service

import com.pabi.common.exception.DuplicateUserEmailException
import com.pabi.common.exception.DuplicateUserNickNameException
import com.pabi.user.domain.dto.SignUpUserDto.SignUpUserCommand
import com.pabi.user.domain.dto.SignUpUserDto.SignUpUserInfo
import com.pabi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class SignUpUserService(
    private val userRepository: UserRepository,
) {

    fun signUpUser(request: SignUpUserCommand): SignUpUserInfo {
        with(request) {
            userRepository.findByNickNameOrEmail(nickName, email)?.let {
                if (it.nickName == nickName) {
                    throw DuplicateUserNickNameException()
                }

                if (it.email == email) {
                    throw DuplicateUserEmailException()
                }
            }

            val user = toEntity()
            userRepository.save(user)

            return SignUpUserInfo(user)
        }
    }
}

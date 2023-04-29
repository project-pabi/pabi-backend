package com.pabi.user.domain.service

import com.pabi.common.exception.NotFoundUserException
import com.pabi.user.domain.dto.ModifyUserDto.ModifyUserCommand
import com.pabi.user.domain.dto.ModifyUserDto.ModifyUserInfo
import com.pabi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ModifyUserService(
    val userRepository: UserRepository,
) {

    fun modifyUser(email: String, request: ModifyUserCommand): ModifyUserInfo {
        with(request) {
            val user = userRepository.findByEmail(email) ?: throw NotFoundUserException()
            user.modifyUser(this)

            userRepository.save(user)

            return ModifyUserInfo(user)
        }
    }
}

package com.pabi.user.domain.service

import com.pabi.common.exception.NotFoundUserEmailException
import com.pabi.user.domain.dto.FindUserDto
import com.pabi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class FindUserService(
    private val userRepository: UserRepository,
) {

    fun findUser(request : FindUserDto.FindUserCommand) : FindUserDto.FindUserInfo {
        with(request) {
            val user = userRepository.findUserProfile(email) ?: throw NotFoundUserEmailException()
            return FindUserDto.FindUserInfo(user);
        }
    }
}

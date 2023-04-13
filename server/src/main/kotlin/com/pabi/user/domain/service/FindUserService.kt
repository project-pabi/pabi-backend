package com.pabi.user.domain.service

import com.pabi.common.exception.NotFoundUserEmailException
import com.pabi.common.exception.NotFoundUserException
import com.pabi.user.domain.dto.FindUserDto
import com.pabi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class FindUserService(
    private val userRepository: UserRepository,
) {
    fun findUser(userId: Long): FindUserDto.FindUserInfo {
        val user = userRepository.findByIdAndWithdrawalFalse(userId) ?: throw NotFoundUserException()
        return FindUserDto.FindUserInfo(user)
    }
}

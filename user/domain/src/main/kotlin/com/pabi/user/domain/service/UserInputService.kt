package com.pabi.user.domain.service

import com.pabi.common.exception.DuplicateUserEmailException
import com.pabi.common.exception.DuplicateUserNickNameException
import com.pabi.user.domain.entity.User
import com.pabi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserInputService(
    private val userRepository: UserRepository,
) {

    fun inputUser(user: User): User {
        with(user) {
            userRepository.findByNickNameOrEmail(nickName, email)?.let {
                if (it.nickName == nickName) {
                    throw DuplicateUserNickNameException()
                }

                if (it.email == email) {
                    throw DuplicateUserEmailException()
                }
            }

            userRepository.save(this)
        }

        return user
    }
}
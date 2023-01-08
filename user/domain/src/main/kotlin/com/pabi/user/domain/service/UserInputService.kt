package com.pabi.user.domain.service

import com.pabi.user.domain.entity.User
import com.pabi.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserInputService(
    private val userRepository: UserRepository,
) {

    fun inputUser(user: User): User {
        with(user) {
            userRepository.findByNickNameAndEmail(nickName, email)?.let {
                if (it.nickName == nickName) {
                    throw Exception()
                }

                if (it.email == email) {
                    throw Exception()
                }
            }

            userRepository.save(this)
        }

        return user
    }
}
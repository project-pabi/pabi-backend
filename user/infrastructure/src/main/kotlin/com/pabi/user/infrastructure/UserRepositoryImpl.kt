package com.pabi.user.infrastructure

import com.pabi.user.domain.entity.User
import com.pabi.user.domain.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {

    override fun findByIdAndWithdrawalFalse(userId: Long): User? {
        return userJpaRepository.findByIdAndWithdrawalFalse(userId)
    }

    override fun findByNickNameAndEmail(nickName: String, email: String): User? {
        return userJpaRepository.findByNickNameAndEmail(nickName, email)
    }

    override fun save(user: User): User {
        return userJpaRepository.save(user)
    }
}
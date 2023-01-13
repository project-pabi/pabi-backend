package com.pabi.server

import com.pabi.common.jwt.JwtUserRepository
import com.pabi.user.infrastructure.UserJpaRepository
import org.springframework.stereotype.Repository

@Repository
class JwtUserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository,
) : JwtUserRepository {

    override fun validTokenByEmail(email: String): Boolean {
        val user = userJpaRepository.findByEmail(email) ?: return false
        if (user.withdrawal) {
            return false
        }

        return true
    }
}
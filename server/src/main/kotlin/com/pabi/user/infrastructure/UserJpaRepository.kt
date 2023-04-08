package com.pabi.user.infrastructure

import com.pabi.user.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository : JpaRepository<User, Long> {

    fun findByIdAndWithdrawalFalse(userId: Long): User?
    fun findByNickNameOrEmail(nickName: String, email: String): User?
    fun findByEmail(email: String): User?
    fun findByIdAndWithdrawalIsFalse(userId: Long): User?
}

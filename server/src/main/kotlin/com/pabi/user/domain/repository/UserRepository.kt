package com.pabi.user.domain.repository

import com.pabi.user.domain.entity.User

interface UserRepository {
    fun findByIdAndWithdrawalFalse(userId: Long): User?
    fun findByNickNameOrEmail(nickName: String, email: String): User?
    fun save(user: User): User
    fun findByEmail(email: String): User?
    fun findUserProfile(email: String): User?
}

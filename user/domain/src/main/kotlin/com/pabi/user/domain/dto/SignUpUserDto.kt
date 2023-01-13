package com.pabi.user.domain.dto

import com.pabi.user.domain.entity.User

class SignUpUserDto {

    data class SignUpUserCommand (
        val email: String,
        val password: String,
        val nickName: String,
    ) {
        fun toEntity(): User {
            return User(
                email = email,
                nickName = nickName,
                password = password,
            )
        }
    }

    data class SignUpUserInfo(
        val id: Long,
        val email: String,
        val nickName: String,
    ) {
        constructor(
            user: User
        ) : this(
            id = user.id!!,
            email = user.email,
            nickName = user.nickName
        )
    }
}
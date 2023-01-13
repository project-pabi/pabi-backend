package com.pabi.user.domain.dto

import com.pabi.user.domain.entity.User

class ModifyUserDto {

    data class ModifyUserCommand(
        val nickName: String,
    )

    data class ModifyUserInfo(
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
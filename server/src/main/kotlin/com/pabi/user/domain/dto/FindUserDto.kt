package com.pabi.user.domain.dto

import com.pabi.user.domain.entity.User

class FindUserDto {

    data class FindUserCommand(
        val email: String,
    )

    data class FindUserInfo(
        val email: String,
        val nickName : String,
        val profileComment: String,
        val rating: Float,
    ){
        constructor(
            user: User
        ) : this(
            email = user.email,
            nickName = user.nickName,
            profileComment = user.profileComment,
            rating = user.rating,
        )
    }
}

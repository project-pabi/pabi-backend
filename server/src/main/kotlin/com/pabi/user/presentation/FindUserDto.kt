package com.pabi.user.presentation
import com.pabi.user.domain.dto.FindUserDto.FindUserInfo

class FindUserDto {
    data class FindUserResponse(
        val email: String,
        val nickName: String,
        val profileComment: String,
        val rating: Float,
    ) {

        constructor(
            findUserInfo: FindUserInfo,
        ) : this(
            email = findUserInfo.email,
            nickName = findUserInfo.nickName,
            profileComment = findUserInfo.profileComment,
            rating = findUserInfo.rating
        )
    }
}

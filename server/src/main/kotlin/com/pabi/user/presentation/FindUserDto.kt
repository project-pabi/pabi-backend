package com.pabi.user.presentation

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

class FindUserDto {

    data class FindUserRequest(
        @Email(message = "이메일 형식이 아닙니다.")
        @NotBlank(message = "아이디를 입력해 주세요.")
        @ApiModelProperty(value = "이메일 아이디", example = "test@gmail.com", required = true)
        val email: String,
    ) {

        constructor() : this(
            email = ""
        )

        fun toCommand(): com.pabi.user.domain.dto.FindUserDto.FindUserCommand {
            return com.pabi.user.domain.dto.FindUserDto.FindUserCommand(
                email = email
            )
        }
    }

    data class FindUserResponse(
        val email: String,
        val nickName: String,
        val profileComment: String,
        val rating: Float,
    ) {

        constructor(
            findUserInfo: com.pabi.user.domain.dto.FindUserDto.FindUserInfo,
        ) : this(
            email = findUserInfo.email,
            nickName = findUserInfo.nickName,
            profileComment = findUserInfo.profileComment,
            rating = findUserInfo.rating
        )
    }
}

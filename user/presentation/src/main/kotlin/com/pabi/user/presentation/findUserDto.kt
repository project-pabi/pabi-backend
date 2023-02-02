package com.pabi.user.presentation

import com.pabi.user.domain.dto.SignInUserDto.*
import com.pabi.common.response.Token
import com.pabi.user.domain.dto.FindUserDto
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class findUserDto {

    data class FindUserRequest(
        @Email(message = "이메일 형식이 아닙니다.")
        @NotBlank(message = "아이디를 입력해 주세요.")
        @ApiModelProperty(value = "이메일 아이디", example = "test@gmail.com", required = true)
        val email: String,
    ) {

        constructor(): this(
            email = ""
        )

        fun toCommand(): FindUserDto.FindUserCommand {
            return FindUserDto.FindUserCommand(
                email = email,
            )
        }
    }

    data class FindUserResponse(
        @ApiModelProperty(value = "토큰", required = true)
        val email : String,
        val nickName : String,
        val profileComment : String,
        val rating : Float
    ) {

        constructor(
                FindUserInfo: FindUserDto.FindUserInfo
        ) : this(
                email = this.email,
                 nickName = this.nickName
                 profileComment = this.profileComment
                 rating = this.rating
        )
    }
}
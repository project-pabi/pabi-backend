package com.pabi.user.presentation

import com.pabi.common.response.Token
import com.pabi.user.domain.dto.SignInUserDto.SignInUserCommand
import com.pabi.user.domain.dto.SignInUserDto.SignInUserInfo
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SignOutUserDto {

    data class SignOutUserRequest(
        @Email(message = "이메일 형식이 아닙니다.")
        @NotBlank(message = "아이디를 입력해 주세요.")
        @ApiModelProperty(value = "이메일 아이디", example = "test@gmail.com", required = true)
        val email: String,

        @Size(min = 8, max = 20, message = "8~20자 이내로 입력해 주세요.")
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        @ApiModelProperty(value = "비밀번호", example = "test12!@", required = true)
        val password: String,
    ) {

        constructor() : this(
            email = "",
            password = ""
        )

        fun toCommand(): SignInUserCommand {
            return SignInUserCommand(
                email = email,
                password = password,
            )
        }
    }

    data class SignInUserResponse(
        @ApiModelProperty(value = "토큰", required = true)
        val token: Token,
    ) {

        constructor(
            signInUserInfo: SignInUserInfo
        ) : this(
            token = signInUserInfo.token
        )
    }
}
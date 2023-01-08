package com.pabi.user.presentation

import com.pabi.user.application.SignUpUserDto
import com.pabi.user.application.SignUpUserDto.SignUpUserCommand
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SignUpUserDto {

    data class SignUpUserRequest(
        @Email(message = "이메일 형식이 아닙니다.")
        @NotBlank(message = "아이디를 입력해 주세요.")
        val email: String,

        @Size(min = 8, max = 20, message = "8~20자 이내로 입력해 주세요.")
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        val password: String,

        @NotBlank(message = "닉네임을 입력해 주세요.")
        val nickName: String,
    ) {

        fun toCommand(): SignUpUserCommand {
            return SignUpUserCommand(
                email = email,
                nickName = nickName,
                password = password,
            )
        }
    }

    data class SignUpUserResponse(
        val id: Long,

        val email: String,

        val nickName: String,
    ) {

        constructor(
            signUpUserInfo: SignUpUserDto.SignUpUserInfo
        ) : this(
            id = signUpUserInfo.id,
            email = signUpUserInfo.email,
            nickName = signUpUserInfo.nickName
        )
    }
}
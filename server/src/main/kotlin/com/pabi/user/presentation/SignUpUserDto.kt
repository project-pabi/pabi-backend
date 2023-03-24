package com.pabi.user.presentation

import com.pabi.user.domain.dto.SignUpUserDto.SignUpUserCommand
import com.pabi.user.domain.dto.SignUpUserDto.SignUpUserInfo
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class SignUpUserDto {

    data class SignUpUserRequest(
        @Email(message = "이메일 형식이 아닙니다.")
        @NotBlank(message = "아이디를 입력해 주세요.")
        @ApiModelProperty(value = "이메일 아이디", example = "test@gmail.com", required = true)
        val email: String = "",

        @Size(min = 8, max = 20, message = "8~20자 이내로 입력해 주세요.")
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        @ApiModelProperty(value = "비밀번호", example = "test12!@", required = true)
        val password: String = "",

        @NotBlank(message = "닉네임을 입력해 주세요.")
        @ApiModelProperty(value = "닉네임", example = "닉네임", required = true)
        val nickName: String = "",
    ) {

        fun toCommand(): SignUpUserCommand {
            return SignUpUserCommand(
                email = email,
                nickName = nickName,
                password = password
            )
        }
    }

    data class SignUpUserResponse(
        @ApiModelProperty(value = "PK ID", required = true)
        val id: Long,

        @ApiModelProperty(value = "이메일 아이디", required = true)
        val email: String,

        @ApiModelProperty(value = "닉네임", required = true)
        val nickName: String,
    ) {

        constructor(
            signUpUserInfo: SignUpUserInfo,
        ) : this(
            id = signUpUserInfo.id,
            email = signUpUserInfo.email,
            nickName = signUpUserInfo.nickName
        )
    }
}

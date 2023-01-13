package com.pabi.user.presentation

import com.pabi.user.domain.dto.ModifyUserDto.ModifyUserCommand
import com.pabi.user.domain.dto.ModifyUserDto.ModifyUserInfo
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email

class ModifyUserDto {

    data class ModifyUserRequest(
        @Email(message = "이메일 형식이 아닙니다.")
        @ApiModelProperty(value = "이메일 아이디", required = true, example = "test@gmail.com")
        val email: String,
        @ApiModelProperty(value = "닉네임", required = true, example = "닉네임수정")
        val nickName: String,
    ) {

        constructor() : this(
            email = "",
            nickName = "",
        )

        fun toCommand(): ModifyUserCommand {
            return ModifyUserCommand(
                email = email,
                nickName = nickName,
            )
        }
    }

    data class ModifyUserResponse(
        @ApiModelProperty(value = "PK ID", required = true)
        val id: Long,

        @ApiModelProperty(value = "이메일 아이디", required = true)
        val email: String,

        @ApiModelProperty(value = "닉네임", required = true)
        val nickName: String,
    ) {

        constructor(
            modifyUserInfo: ModifyUserInfo
        ) : this(
            id = modifyUserInfo.id,
            email = modifyUserInfo.email,
            nickName = modifyUserInfo.nickName,
        )
    }
}
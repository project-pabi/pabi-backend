package com.pabi.user.presentation

import com.pabi.user.domain.dto.ModifyUserDto.ModifyUserCommand
import com.pabi.user.domain.dto.ModifyUserDto.ModifyUserInfo
import io.swagger.annotations.ApiModelProperty

class ModifyUserDto {

    data class ModifyUserRequest(
        @ApiModelProperty(value = "닉네임", required = true, example = "닉네임수정")
        val nickName: String,
    ) {

        constructor() : this(
            nickName = ""
        )

        fun toCommand(): ModifyUserCommand {
            return ModifyUserCommand(
                nickName = nickName
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
            modifyUserInfo: ModifyUserInfo,
        ) : this(
            id = modifyUserInfo.id,
            email = modifyUserInfo.email,
            nickName = modifyUserInfo.nickName
        )
    }
}

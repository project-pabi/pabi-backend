package com.pabi.user.domain.dto

import com.pabi.common.response.Token


class SignInUserDto {

    data class SignInUserCommand(
        val email: String,
        val password: String,
    )

    data class SignInUserInfo(
        val token: Token,
    ) {

    }
}
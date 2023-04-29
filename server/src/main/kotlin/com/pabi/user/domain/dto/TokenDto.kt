package com.pabi.user.domain.dto

import com.pabi.common.response.Token

class TokenDto {

    data class TokenReissueInfo(
        val token: Token,
    )
}

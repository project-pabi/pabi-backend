package com.pabi.user.presentation.common

import com.pabi.user.domain.dto.common.Token
import java.util.*

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val expiresIn: Long,
    val issuedAt: Date
) {

    constructor(
        token: Token
    ) : this(
        accessToken = token.accessToken,
        refreshToken = token.refreshToken,
        tokenType = token.tokenType,
        expiresIn = token.expiresIn,
        issuedAt = token.issuedAt,
    )
}

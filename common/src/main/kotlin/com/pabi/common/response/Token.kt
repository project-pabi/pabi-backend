package com.pabi.common.response

import java.util.*

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String,
    val expiresIn: Long,
    val issuedAt: Date
) {

    constructor(
    ) : this(
        accessToken = "",
        refreshToken = "",
        tokenType = "",
        expiresIn = 0,
        issuedAt = Date(),
    )
}

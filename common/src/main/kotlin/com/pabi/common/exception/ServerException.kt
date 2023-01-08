package com.pabi.common.exception

sealed class ServerException(
    val code: Int,
    override val message: String,
) : RuntimeException(message)

data class DuplicateUserNickNameException(
    override val message: String = "이미 존재 하는 닉네임 입니다.",
) : ServerException(200, message)

data class DuplicateUserEmailException(
    override val message: String = "이미 존재 하는 이메일 입니다.",
) : ServerException(200, message)
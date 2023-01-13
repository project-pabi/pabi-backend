package com.pabi.common.jwt

interface JwtUserRepository {

    fun validTokenByEmail(email: String): Boolean
}
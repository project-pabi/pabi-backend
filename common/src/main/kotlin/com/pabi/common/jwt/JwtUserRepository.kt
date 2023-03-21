package com.pabi.common.jwt

interface JwtUserRepository {

    fun validTokenByEmail(email: String): Boolean
    fun userRolesByEmail(email: String): String
}
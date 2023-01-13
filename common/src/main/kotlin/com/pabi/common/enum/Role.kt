package com.pabi.common.enum

enum class Role {
    ROLE_USER, ROLE_ADMIN;

    companion object {

        operator fun invoke(priority: String) = Role.valueOf(priority.uppercase())
    }
}
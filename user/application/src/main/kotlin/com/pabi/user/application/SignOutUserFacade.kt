package com.pabi.user.application

import com.pabi.user.domain.service.SignOutUserService
import org.springframework.stereotype.Service

@Service
class SignOutUserFacade(
    private val signOutUserService: SignOutUserService,
) {

    fun signOutUser(
        accessToken: String
    ) = signOutUserService.signOutUser(accessToken)
}

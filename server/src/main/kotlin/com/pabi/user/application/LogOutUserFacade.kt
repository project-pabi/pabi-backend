package com.pabi.user.application

import com.pabi.user.domain.service.LogOutUserService
import org.springframework.stereotype.Service

@Service
class LogOutUserFacade(
    private val logOutUserService: LogOutUserService,
) {

    fun logOutUser(email: String, requestAccessToken: String) = logOutUserService.logOutUser(email, requestAccessToken)
}

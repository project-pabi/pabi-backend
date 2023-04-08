package com.pabi.user.application

import com.pabi.user.domain.service.FindUserService
import org.springframework.stereotype.Service

@Service
class FindUserFacade(private val findUserService: FindUserService) {
    fun findUser(userId: Long) = findUserService.findUser(userId)
}

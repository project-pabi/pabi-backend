package com.pabi.user.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/user")
@RestController
class InputUserController {
    @GetMapping("/test")
    fun test(): String {
        return "test"
    }
}
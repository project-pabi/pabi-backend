package com.pabi.common.exception

import com.pabi.common.response.CommonResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = KotlinLogging.logger {}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleInternalServerException(e: Exception): CommonResponse<Nothing> {
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)
    }

    @ExceptionHandler(ServerException::class)
    fun handleServerException(ex: ServerException): ResponseEntity<Any> {
        val response = CommonResponse.fail(ex.message, ex.javaClass.simpleName)
        return ResponseEntity(response, null, ex.code)
    }
}
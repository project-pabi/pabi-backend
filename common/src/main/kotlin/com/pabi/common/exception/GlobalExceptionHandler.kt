package com.pabi.common.exception

import com.pabi.common.response.CommonResponse
import com.pabi.common.response.Error
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ServerWebInputException

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = KotlinLogging.logger {}

    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // @ExceptionHandler(Exception::class)
    // fun handleInternalServerException(e: Exception): CommonResponse<Nothing> {
    //     return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR)
    // }

    @ExceptionHandler(ServerException::class)
    fun handleServerException(ex: ServerException): ResponseEntity<Any> {
        val response = CommonResponse.fail(ex.message, ex.javaClass.simpleName)
        return ResponseEntity(response, null, ex.code)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [ServerWebInputException::class])
    fun methodArgumentNotValidException(
        e: ServerWebInputException,
    ): CommonResponse<Nothing> {
        return CommonResponse.fail(ErrorCode.COMMON_NULL_PARAMETER)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): CommonResponse<String> {
        val errors = mutableListOf<Error>()
        e.allErrors.forEach {
            val error = Error(
                field = (it as FieldError).field,
                message = it.defaultMessage,
                value = it.rejectedValue
            )
            errors.add(error)
        }

        return CommonResponse.fail(errors.toString(), ErrorCode.COMMON_INVALID_PARAMETER)
    }
}

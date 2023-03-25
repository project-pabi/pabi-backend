package com.pabi.common.response

import com.pabi.common.exception.ErrorCode

data class CommonResponse<T>(
    val result: Result,
    val data: T?,
    val message: String,
    val errorCode: String?,
) {

    companion object {

        // status 200 + success (message가 없을 경우)
        fun <T> success(data: T): CommonResponse<T> {
            return CommonResponse(
                result = Result.SUCCESS,
                data = data,
                message = "",
                errorCode = null
            )
        }

        fun <T> success(data: T, message: String): CommonResponse<T> {
            return CommonResponse(
                result = Result.SUCCESS,
                data = data,
                message = message,
                errorCode = null
            )
        }

        fun success(message: String): CommonResponse<Nothing> {
            return CommonResponse(
                result = Result.SUCCESS,
                data = null,
                message = message,
                errorCode = null
            )
        }

        fun fail(message: String, exceptionName: String): CommonResponse<Nothing> {
            return CommonResponse(
                result = Result.FAIL,
                data = null,
                message = message,
                errorCode = exceptionName
            )
        }

        fun <T> fail(data: T, errorCode: ErrorCode): CommonResponse<T> {
            return CommonResponse(
                result = Result.FAIL,
                data = data,
                message = errorCode.errorMsg,
                errorCode = errorCode.name
            )
        }

        fun fail(errorCode: ErrorCode): CommonResponse<Nothing> {
            return CommonResponse(
                result = Result.FAIL,
                data = null,
                message = errorCode.getErrorMsg(),
                errorCode = errorCode.name
            )
        }
    }

    enum class Result {
        SUCCESS, FAIL
    }
}

data class Error(
    val field: String? = null,
    val message: String? = null,
    val value: Any? = null,
)

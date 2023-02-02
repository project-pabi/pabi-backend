package com.pabi.common.response

import com.pabi.common.exception.ErrorCode

data class CommonResponse<T>(
    val result: Result,
    val data: T?,
    val message: String,
    val errorCode: String?,
) {

    companion object {

        //status 200 + success (message가 없을 경우)
        fun <T> success(data: T): CommonResponse<T> {
            return CommonResponse(
                result = Result.SUCCESS,
                data = data,
                message = "",
                errorCode = null,
            )
        }

        fun <T> success(data: T): CommonResponse<T> {
            return CommonResponse(
                result = Result.SUCCESS,
                data = data,
                message = "",
                errorCode = null,
            )
        }

        fun fail(message: String, exceptionName: String): CommonResponse<Nothing> {
            return CommonResponse(
                result = Result.FAIL,
                data = null,
                message = message,
                errorCode = exceptionName,
            )
        }

        fun fail(errorCode: ErrorCode): CommonResponse<Nothing> {
            return CommonResponse(
                result = Result.FAIL,
                data = null,
                message = errorCode.getErrorMsg(),
                errorCode = errorCode.name,
            )
        }
    }

    enum class Result {
        SUCCESS, FAIL
    }
}
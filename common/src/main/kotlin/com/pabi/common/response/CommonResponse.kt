package com.pabi.common.response

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
    }

    enum class Result {
        SUCCESS, FAIL
    }
}
package com.xlorit.template.core.error

import retrofit2.HttpException
import java.io.IOException

object ErrorMapper {

    fun map(t: Throwable): AppError {
        return when (t) {
            is IOException -> AppError.Network
            is HttpException -> {
                when (t.code()) {
                    401 -> AppError.Unauthorized
                    else -> AppError.Api(
                        t.response()?.errorBody()?.string()
                            ?: "Something went wrong"
                    )
                }
            }
            else -> AppError.Unknown
        }
    }
}

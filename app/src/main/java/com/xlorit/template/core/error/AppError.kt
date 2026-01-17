package com.xlorit.template.core.error

sealed class AppError {
    object Network : AppError()
    object Unauthorized : AppError()
    data class Api(val message: String) : AppError()
    object Unknown : AppError()
}

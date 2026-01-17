package com.xlorit.template.core.result

import com.xlorit.template.core.error.AppError

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val error: AppError) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

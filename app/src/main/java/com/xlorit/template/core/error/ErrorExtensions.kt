package com.xlorit.template.core.error

fun AppError.toUiMessage(): String =
    when (this) {
        AppError.Network -> "No internet connection"
        AppError.Unauthorized -> "Session expired"
        is AppError.Api -> message
        AppError.Unknown -> "Something went wrong"
    }

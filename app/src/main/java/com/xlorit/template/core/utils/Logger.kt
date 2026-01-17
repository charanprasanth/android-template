package com.xlorit.template.core.utils

import timber.log.Timber

object Logger {
    fun d(message: String) = Timber.d(message)
    fun e(t: Throwable) = Timber.e(t)
}
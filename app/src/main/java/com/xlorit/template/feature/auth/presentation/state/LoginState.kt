package com.xlorit.template.feature.auth.presentation.state

data class LoginState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val error: String? = null
)

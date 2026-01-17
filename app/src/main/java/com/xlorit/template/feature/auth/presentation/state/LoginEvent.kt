package com.xlorit.template.feature.auth.presentation.state

sealed class LoginEvent {
    data class EmailChanged(val value: String) : LoginEvent()
    data class PasswordChanged(val value: String) : LoginEvent()
    object Submit : LoginEvent()
}

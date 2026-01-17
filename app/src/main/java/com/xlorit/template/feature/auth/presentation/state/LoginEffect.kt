package com.xlorit.template.feature.auth.presentation.state

sealed class LoginEffect {
    object NavigateToHome : LoginEffect()
}

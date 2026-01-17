package com.xlorit.template.feature.auth.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlorit.template.core.error.toUiMessage
import com.xlorit.template.core.result.Result
import com.xlorit.template.feature.auth.domain.usecase.LoginUseCase
import com.xlorit.template.feature.auth.presentation.state.LoginEffect
import com.xlorit.template.feature.auth.presentation.state.LoginEvent
import com.xlorit.template.feature.auth.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged ->
                _state.update { it.copy(email = event.value) }

            is LoginEvent.PasswordChanged ->
                _state.update { it.copy(password = event.value) }

            LoginEvent.Submit -> submit()
        }
    }

    private fun submit() = viewModelScope.launch {
        _state.update { it.copy(loading = true, error = null) }

        when (val res = login(
            _state.value.email,
            _state.value.password
        )) {
            is Result.Success<*> -> {
                _state.update { it.copy(loading = false) }
                _effect.send(LoginEffect.NavigateToHome)
            }

            is Result.Error -> {
                _state.update {
                    it.copy(
                        loading = false,
                        error = res.error.toUiMessage()
                    )
                }
            }

            is Result.Loading -> {}
        }
    }
}

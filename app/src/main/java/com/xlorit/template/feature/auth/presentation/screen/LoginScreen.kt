package com.xlorit.template.feature.auth.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.xlorit.template.feature.auth.presentation.state.LoginEffect
import com.xlorit.template.feature.auth.presentation.state.LoginEvent
import com.xlorit.template.feature.auth.presentation.vm.LoginViewModel

@Composable
fun LoginScreen(
    onSuccess: () -> Unit,
    vm: LoginViewModel = hiltViewModel()
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.effect.collect { effect ->
            when (effect) {
                LoginEffect.NavigateToHome -> onSuccess()
            }
        }
    }

    Column {
        TextField(
            value = state.email,
            onValueChange = {
                vm.onEvent(LoginEvent.EmailChanged(it))
            }
        )

        TextField(
            value = state.password,
            onValueChange = {
                vm.onEvent(LoginEvent.PasswordChanged(it))
            }
        )

        Button(
            onClick = {
                vm.onEvent(LoginEvent.Submit)
            }
        ) {
            Text(text = "Login")
        }

        state.error?.let {
            Text(text = it, color = Color.Red)
        }
    }
}

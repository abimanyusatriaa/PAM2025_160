package com.example.tugas.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas.repositori.RepositoriUser
import kotlinx.coroutines.launch

class LoginViewModel(private val repositoriUser: RepositoriUser) : ViewModel() {
    var loginUiState by mutableStateOf(LoginUiState())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun updateUiState(email: String, password: String) {
        loginUiState = LoginUiState(email = email, password = password)
        errorMessage = null
    }

    fun login(onLoginSuccess: (String) -> Unit) {
        viewModelScope.launch {
            val user = repositoriUser.getUserByEmail(loginUiState.email)
            if (user != null && user.password == loginUiState.password) {
                onLoginSuccess(user.username)
            } else {
                errorMessage = "Email atau Password salah"
            }
        }
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = ""
)

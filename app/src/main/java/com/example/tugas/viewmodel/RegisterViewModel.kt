package com.example.tugas.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas.repositori.RepositoriUser
import com.example.tugas.room.User
import kotlinx.coroutines.launch

class RegisterViewModel(private val repositoriUser: RepositoriUser) : ViewModel() {
    var registerUiState by mutableStateOf(RegisterUiState())
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun updateUiState(details: RegisterDetails) {
        registerUiState = RegisterUiState(registerDetails = details)
        errorMessage = null
    }

    fun register(onRegisterSuccess: () -> Unit) {
        viewModelScope.launch {
            if (validateInput()) {
                val existingUser = repositoriUser.getUserByUsername(registerUiState.registerDetails.username)
                if (existingUser == null) {
                    repositoriUser.insertUser(registerUiState.registerDetails.toUser())
                    onRegisterSuccess()
                } else {
                    errorMessage = "Username sudah digunakan"
                }
            } else {
                errorMessage = "Semua field harus diisi"
            }
        }
    }

    private fun validateInput(): Boolean {
        val details = registerUiState.registerDetails
        return details.username.isNotBlank() && details.password.isNotBlank() && details.email.isNotBlank()
    }
}

data class RegisterUiState(
    val registerDetails: RegisterDetails = RegisterDetails()
)

data class RegisterDetails(
    val username: String = "",
    val password: String = "",
    val email: String = ""
)

fun RegisterDetails.toUser(): User = User(
    username = username,
    password = password,
    email = email
)

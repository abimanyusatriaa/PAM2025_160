package com.example.tugas.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugas.R
import com.example.tugas.view.route.DestinasiLogin
import com.example.tugas.viewmodel.LoginViewModel
import com.example.tugas.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanLogin(
    navigateToHome: (String) -> Unit,
    navigateToRegister: () -> Unit,
    viewModel: LoginViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize() // Fill size to center vertically
                .padding(24.dp), // More padding
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 32.dp),
                tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Scentnesia",
                style = androidx.compose.material3.MaterialTheme.typography.displaySmall,
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            val uiState = viewModel.loginUiState
            
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { viewModel.updateUiState(it, uiState.password) },
                label = { Text(stringResource(R.string.email)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                singleLine = true,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = uiState.password,
                onValueChange = { viewModel.updateUiState(uiState.email, it) },
                label = { Text(stringResource(R.string.password)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )

            if (viewModel.errorMessage != null) {
                Text(
                    text = viewModel.errorMessage!!,
                    color = androidx.compose.ui.graphics.Color.Red,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            Button(
                onClick = { viewModel.login(navigateToHome) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_login))
            }

            TextButton(
                onClick = navigateToRegister,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.belum_punya_akun))
            }
        }
    }
}

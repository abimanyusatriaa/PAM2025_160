package com.example.tugas.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugas.R
import com.example.tugas.view.route.DestinasiRegister
import com.example.tugas.viewmodel.RegisterViewModel
import com.example.tugas.viewmodel.provider.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanRegister(
    navigateBack: () -> Unit,
    viewModel: RegisterViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize() // Center content
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Custom Back Button since we removed TopAppBar
            androidx.compose.foundation.layout.Box(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                 androidx.compose.material3.IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.btn_back),
                        tint = androidx.compose.material3.MaterialTheme.colorScheme.primary
                    )
                }
            }

            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.PersonAdd,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 32.dp),
                tint = androidx.compose.material3.MaterialTheme.colorScheme.secondary
            )

            Text(
                text = "Scentnesia",
                style = androidx.compose.material3.MaterialTheme.typography.displaySmall,
                color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            val uiState = viewModel.registerUiState
            val details = uiState.registerDetails

            OutlinedTextField(
                value = details.username,
                onValueChange = { viewModel.updateUiState(details.copy(username = it)) },
                label = { Text(stringResource(R.string.username)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                singleLine = true,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = details.email,
                onValueChange = { viewModel.updateUiState(details.copy(email = it)) },
                label = { Text(stringResource(R.string.email)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                singleLine = true,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = details.password,
                onValueChange = { viewModel.updateUiState(details.copy(password = it)) },
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
                onClick = { viewModel.register(navigateBack) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.btn_register))
            }

            TextButton(
                onClick = navigateBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.sudah_punya_akun))
            }
        }
    }
}

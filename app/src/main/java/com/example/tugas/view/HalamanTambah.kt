package com.example.tugas.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugas.R
import com.example.tugas.view.route.DestinasiTambah
import com.example.tugas.viewmodel.TambahViewModel
import com.example.tugas.viewmodel.provider.PenyediaViewModel
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.filled.ArrowBack
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanTambah(
    navigateBack: () -> Unit,
    viewModel: TambahViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
             // Custom Header with Back Button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                 IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.btn_back),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(DestinasiTambah.titleRes),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            FormParfum(
                detail = uiState,
                onValueChange = viewModel::updateUi,
                onSubmit = {
                    viewModel.simpanParfum()
                    navigateBack()
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

package com.example.tugas.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugas.R
import com.example.tugas.view.route.DestinasiEdit
import com.example.tugas.viewmodel.TambahViewModel
import com.example.tugas.viewmodel.provider.PenyediaViewModel
import com.example.tugas.viewmodel.toDetailParfum
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanEdit(
    parfum: com.example.tugas.room.Parfum,
    navigateBack: () -> Unit,
    viewModel: TambahViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    var deleteConfirmationRequired by remember { mutableStateOf(false) }
    var updateConfirmationRequired by remember { mutableStateOf(false) }


    LaunchedEffect(parfum) {
        viewModel.updateUi(parfum.toDetailParfum())
    }

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
                    text = stringResource(DestinasiEdit.titleRes),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            FormParfum(
                detail = uiState,
                onValueChange = viewModel::updateUi,
                onSubmit = {
                    updateConfirmationRequired = true
                },
                onDelete = {
                    deleteConfirmationRequired = true
                },
                modifier = Modifier.padding(padding)
            )

            if (updateConfirmationRequired) {
                UpdateConfirmationDialog(
                    onUpdateConfirm = {
                        updateConfirmationRequired = false
                        viewModel.updateParfum()
                        navigateBack()
                    },
                    onUpdateCancel = { updateConfirmationRequired = false },
                    modifier = Modifier.padding(padding)
                )
            }

            if (deleteConfirmationRequired) {
                DeleteConfirmationDialog(
                    onDeleteConfirm = {
                        deleteConfirmationRequired = false
                        viewModel.deleteParfum()
                        navigateBack()
                    },
                    onDeleteCancel = { deleteConfirmationRequired = false },
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = { Text(stringResource(R.string.perhatian)) },
        text = { Text(stringResource(R.string.konfirmasi_hapus)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(R.string.tidak))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(R.string.ya))
            }
        }
    )
}

@Composable
private fun UpdateConfirmationDialog(
    onUpdateConfirm: () -> Unit,
    onUpdateCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = { Text(stringResource(R.string.perhatian)) },
        text = { Text(stringResource(R.string.konfirmasi_update)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onUpdateCancel) {
                Text(text = stringResource(R.string.tidak))
            }
        },
        confirmButton = {
            TextButton(onClick = onUpdateConfirm) {
                Text(text = stringResource(R.string.ya))
            }
        }
    )
}





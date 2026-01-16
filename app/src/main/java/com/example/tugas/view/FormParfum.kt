package com.example.tugas.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tugas.viewmodel.DetailParfum

@Composable
fun FormParfum(
    detail: DetailParfum,
    onValueChange: (DetailParfum) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
    onDelete: (() -> Unit)? = null
) {
    Column(
        modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        OutlinedTextField(
            value = detail.namaParfum,
            onValueChange = { onValueChange(detail.copy(namaParfum = it)) },
            label = { Text("Nama Parfum") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = detail.aroma,
            onValueChange = { onValueChange(detail.copy(aroma = it)) },
            label = { Text("Aroma") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = detail.harga,
            onValueChange = { onValueChange(detail.copy(harga = it)) },
            label = { Text("Harga") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = detail.deskripsi,
            onValueChange = { onValueChange(detail.copy(deskripsi = it)) },
            label = { Text("Deskripsi") },
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 3
        )


        Button(
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }

        if (onDelete != null) {
            OutlinedButton(
                onClick = onDelete,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Hapus")
            }
        }
    }
}

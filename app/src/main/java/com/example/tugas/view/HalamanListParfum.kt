package com.example.tugas.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugas.R
import com.example.tugas.room.Parfum
import com.example.tugas.view.route.DestinasiListParfum
import com.example.tugas.viewmodel.HomeViewModel
import com.example.tugas.viewmodel.provider.PenyediaViewModel
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanListParfum(
    navigateBack: () -> Unit,
    navigateToTambah: () -> Unit,
    navigateToEdit: (Parfum) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToTambah,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Custom Header
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
                    text = stringResource(DestinasiListParfum.titleRes),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Filter Section
            var selectedFilter by remember { mutableStateOf("") }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedFilter == "",
                    onClick = { 
                        selectedFilter = ""
                        viewModel.updateFilter("") 
                    },
                    label = { Text("Semua") }
                )
                FilterChip(
                    selected = selectedFilter == "segar",
                    onClick = { 
                        selectedFilter = "segar"
                        viewModel.updateFilter("segar") 
                    },
                    label = { Text("Segar") }
                )
                FilterChip(
                    selected = selectedFilter == "manis",
                    onClick = { 
                        selectedFilter = "manis"
                        viewModel.updateFilter("manis") 
                    },
                    label = { Text("Manis") }
                )
            }

            val uiState by viewModel.homeUiState.collectAsState()

            BodyListParfum(
                itemParfum = uiState.listParfum,
                onParfumClick = navigateToEdit,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun BodyListParfum(
    itemParfum: List<Parfum>,
    onParfumClick: (Parfum) -> Unit,
    modifier: Modifier = Modifier
) {
    if (itemParfum.isEmpty()) {
        Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.no_item))
        }
    } else {
        LazyColumn(modifier) {
            items(itemParfum, key = { it.id }) { parfum ->
                DataParfumItem(parfum) { onParfumClick(parfum) }
            }
        }
    }
}

@Composable
fun DataParfumItem(parfum: Parfum, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Placeholder for Image
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Spa,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(48.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = parfum.namaParfum,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
                Text(
                    text = parfum.aroma,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                if (parfum.deskripsi.isNotEmpty()) {
                    Text(
                        text = parfum.deskripsi,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            Text(
                text = java.text.NumberFormat.getCurrencyInstance(java.util.Locale("id", "ID")).format(parfum.harga),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        }
    }
}

package com.example.tugas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas.repositori.RepositoriParfum
import com.example.tugas.room.Parfum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TambahViewModel(
    private val repositoriParfum: RepositoriParfum
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailParfum())
    val uiState: StateFlow<DetailParfum> = _uiState

    fun updateUi(detail: DetailParfum) {
        _uiState.value = detail
    }

    fun simpanParfum() {
        viewModelScope.launch {
            repositoriParfum.insertParfum(_uiState.value.toParfum())
        }
    }

    fun updateParfum() {
        viewModelScope.launch {
            repositoriParfum.updateParfum(_uiState.value.toParfum())
        }
    }

    fun deleteParfum() {
        viewModelScope.launch {
            repositoriParfum.deleteParfum(_uiState.value.toParfum())
        }
    }
}

data class DetailParfum(
    val id: Int = 0,
    val namaParfum: String = "",
    val aroma: String = "",
    val harga: String = "",
    val deskripsi: String = ""
)

fun DetailParfum.toParfum(): Parfum = Parfum(
    id = id,
    namaParfum = namaParfum,
    aroma = aroma,
    harga = harga.toDoubleOrNull() ?: 0.0,
    deskripsi = deskripsi
)

fun Parfum.toDetailParfum(): DetailParfum = DetailParfum(
    id = id,
    namaParfum = namaParfum,
    aroma = aroma,
    harga = harga.toString(),
    deskripsi = deskripsi
)

package com.example.tugas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas.repositori.RepositoriParfum
import com.example.tugas.room.Parfum
import kotlinx.coroutines.flow.*

class HomeViewModel(
    private val repositoriParfum: RepositoriParfum
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val _filterUiState = MutableStateFlow("")

    val homeUiState: StateFlow<HomeUiState> =
        repositoriParfum.getAllParfumStream()
            .filterNotNull()
            .combine(_filterUiState) { listParfum, filter ->
                if (filter.isBlank()) {
                    HomeUiState(listParfum = listParfum)
                } else {
                    HomeUiState(listParfum = listParfum.filter {
                        it.aroma.contains(filter, ignoreCase = true)
                    })
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                HomeUiState()
            )

    fun updateFilter(filter: String) {
        _filterUiState.value = filter
    }

    data class HomeUiState(
        val listParfum: List<Parfum> = listOf()
    )
}

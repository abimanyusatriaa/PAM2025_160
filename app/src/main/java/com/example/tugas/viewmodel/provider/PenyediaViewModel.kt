package com.example.tugas.viewmodel.provider

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tugas.AplikasiParfum
import com.example.tugas.viewmodel.HomeViewModel
import com.example.tugas.viewmodel.TambahViewModel
import com.example.tugas.viewmodel.LoginViewModel
import com.example.tugas.viewmodel.RegisterViewModel

object PenyediaViewModel {

    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(aplikasiParfum().container.repositoriParfum)
        }
        initializer {
            TambahViewModel(aplikasiParfum().container.repositoriParfum)
        }
        initializer {
            LoginViewModel(aplikasiParfum().container.repositoriUser)
        }
        initializer {
            RegisterViewModel(aplikasiParfum().container.repositoriUser)
        }
    }
}

fun CreationExtras.aplikasiParfum(): AplikasiParfum =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiParfum)

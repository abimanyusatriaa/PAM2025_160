package com.example.tugas

import android.app.Application
import com.example.tugas.repositori.ContainerApp
import com.example.tugas.repositori.ContainerDataApp

class AplikasiParfum : Application() {
    /**
     * Appcontainer instance digunakan oleh kelas-kelas lainnya untuk
     * mendapatkan dependensi
     */
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}

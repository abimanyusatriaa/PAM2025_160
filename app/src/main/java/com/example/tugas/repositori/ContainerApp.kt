package com.example.tugas.repositori


import android.content.Context
import com.example.tugas.room.DatabaseParfum

interface ContainerApp {
    val repositoriParfum: RepositoriParfum
    val repositoriUser: RepositoriUser
}

class ContainerDataApp(private val context: Context) : ContainerApp {

    override val repositoriParfum: RepositoriParfum by lazy {
        OfflineRepositoriParfum(
            DatabaseParfum.getDatabase(context).parfumDao()
        )
    }

    override val repositoriUser: RepositoriUser by lazy {
        OfflineRepositoriUser(
            DatabaseParfum.getDatabase(context).userDao()
        )
    }
}



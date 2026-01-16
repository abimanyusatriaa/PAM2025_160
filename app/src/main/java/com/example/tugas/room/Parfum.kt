package com.example.tugas.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parfum")
data class Parfum(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val namaParfum: String,
    val aroma: String,
    val harga: Double,
    val deskripsi: String
): java.io.Serializable

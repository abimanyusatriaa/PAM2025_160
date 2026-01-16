package com.example.tugas.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String, // Note: In a real app, verify properly. For student project, plain text is often accepted unless specified otherwise.
    val email: String
)

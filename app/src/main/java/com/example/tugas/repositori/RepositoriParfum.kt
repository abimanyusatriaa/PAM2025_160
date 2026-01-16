package com.example.tugas.repositori

import com.example.tugas.room.Parfum
import com.example.tugas.room.ParfumDao
import kotlinx.coroutines.flow.Flow

interface RepositoriParfum {
    fun getAllParfumStream(): Flow<List<Parfum>>
    suspend fun insertParfum(parfum: Parfum)
    suspend fun updateParfum(parfum: Parfum)
    suspend fun deleteParfum(parfum: Parfum)
    suspend fun getParfumById(id: Int): Parfum?
}

class OfflineRepositoriParfum(
    private val parfumDao: ParfumDao
) : RepositoriParfum {

    override fun getAllParfumStream(): Flow<List<Parfum>> =
        parfumDao.getAllParfum()

    override suspend fun insertParfum(parfum: Parfum) =
        parfumDao.insert(parfum)

    override suspend fun updateParfum(parfum: Parfum) =
        parfumDao.update(parfum)

    override suspend fun deleteParfum(parfum: Parfum) =
        parfumDao.delete(parfum)

    override suspend fun getParfumById(id: Int): Parfum? =
        parfumDao.getParfumById(id)
}

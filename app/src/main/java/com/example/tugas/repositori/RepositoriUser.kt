package com.example.tugas.repositori

import com.example.tugas.room.User
import com.example.tugas.room.UserDao

interface RepositoriUser {
    suspend fun insertUser(user: User)
    suspend fun getUserByUsername(username: String): User?
    suspend fun getUserByEmail(email: String): User?
}

class OfflineRepositoriUser(private val userDao: UserDao) : RepositoriUser {
    override suspend fun insertUser(user: User) = userDao.insertUser(user)
    override suspend fun getUserByUsername(username: String): User? = userDao.getUserByUsername(username)
    override suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)
}

package com.example.tugas.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ParfumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(parfum: Parfum)

    @Update
    suspend fun update(parfum: Parfum)

    @Delete
    suspend fun delete(parfum: Parfum)

    @Query("SELECT * FROM parfum ORDER BY namaParfum ASC")
    fun getAllParfum(): Flow<List<Parfum>>

    @Query("SELECT * FROM parfum WHERE id = :id")
    suspend fun getParfumById(id: Int): Parfum?

    @Query("SELECT COUNT(*) FROM parfum")
    fun getParfumCount(): Flow<Int>

}

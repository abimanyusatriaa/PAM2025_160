package com.example.tugas.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Parfum::class, User::class],
    version = 5,
    exportSchema = false
)
abstract class DatabaseParfum : RoomDatabase() {

    abstract fun parfumDao(): ParfumDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseParfum? = null

        fun getDatabase(context: Context): DatabaseParfum {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseParfum::class.java,
                    "parfum_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

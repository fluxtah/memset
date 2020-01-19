package com.citizenwarwick.memset.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.citizenwarwick.memset.data.entities.MemoryCardEntity
import com.citizenwarwick.memset.data.entities.MemoryCardEntityDao

@Database(entities = [MemoryCardEntity::class], version = 1)
abstract class MemsetDatabase : RoomDatabase() {
    abstract fun memoryCardDao(): MemoryCardEntityDao

    companion object {
        fun create(applicationContext: Context): MemsetDatabase = Room.databaseBuilder(
            applicationContext,
            MemsetDatabase::class.java, "memsetdb"
        ).build()
    }
}

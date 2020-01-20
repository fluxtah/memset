package com.citizenwarwick.memset.data.entities

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class MemoryCardEntity(
    @PrimaryKey val uid: Long? = null,
    @ColumnInfo(name = "card_json") val cardJson: String?
)

@Dao
interface MemoryCardEntityDao {
    @Query("SELECT * FROM memorycardentity order by uid desc")
    suspend fun getAll(): List<MemoryCardEntity>

    @Insert
    suspend fun insertAll(vararg cards: MemoryCardEntity)

    @Delete
    suspend fun delete(card: MemoryCardEntity)
}

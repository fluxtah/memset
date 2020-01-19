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
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "card_json") val cardJson: String?
)

@Dao
interface MemoryCardEntityDao {
    @Query("SELECT * FROM memorycardentity")
    fun getAll(): List<MemoryCardEntity>

    @Insert
    fun insertAll(vararg cards: MemoryCardEntity)

    @Delete
    fun delete(card: MemoryCardEntity)
}

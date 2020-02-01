package com.citizenwarwick.memset.data.entities

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "cards")
data class MemoryCardEntity(
    @PrimaryKey val id: Long? = null,
    val uuid: String,
    @ColumnInfo(name = "card_json") val cardJson: String?
)

@Dao
interface MemoryCardEntityDao {
    @Query("SELECT * FROM cards order by id desc")
    fun getAll(): LiveData<List<MemoryCardEntity>>

    @Query("SELECT * FROM cards where uuid = :uuid")
    fun getByUuid(uuid: String): LiveData<MemoryCardEntity>

    @Query("SELECT id FROM cards where uuid = :uuid")
    suspend fun getIdForUuid(uuid: String): Long

    @Query("SELECT * FROM cards order by id desc")
    fun getAllAsFlowable(): Flow<List<MemoryCardEntity>>

    @Insert
    suspend fun insertAll(vararg cards: MemoryCardEntity)

    @Update()
    suspend fun updateCard(vararg cards: MemoryCardEntity)

    @Delete
    suspend fun delete(card: MemoryCardEntity)

    @Query("DELETE FROM cards WHERE uuid = :uuid")
    suspend fun deleteByUid(uuid: String)
}

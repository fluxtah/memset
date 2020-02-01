package com.citizenwarwick.memset.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.citizenwarwick.memset.core.di.get
import com.citizenwarwick.memset.core.model.MemoryCard
import com.citizenwarwick.memset.data.MemsetDatabase
import com.citizenwarwick.memset.data.entities.MemoryCardEntity
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MemoryCardRepository(
    private val db: MemsetDatabase = get(),
    private val moshi: Moshi = get()
) {
    suspend fun saveCard(card: MemoryCard) = withContext(Main) { // TODO use IO
        val jsonAdapter = moshi.adapter(MemoryCard::class.java)
        val json = jsonAdapter.toJson(card)

        db.memoryCardDao().insertAll(MemoryCardEntity(uuid = card.uuid, cardJson = json))

    }

    suspend fun updateCard(card: MemoryCard) = withContext(Main) { // TODO use IO
        val jsonAdapter = moshi.adapter(MemoryCard::class.java)
        val json = jsonAdapter.toJson(card)
        val cardId = db.memoryCardDao().getIdForUuid(card.uuid)
        db.memoryCardDao().updateCard(
            MemoryCardEntity(
                id = cardId,
                uuid = card.uuid,
                cardJson = json
            )
        )
    }

    fun getCards(): LiveData<List<MemoryCard>> {
        val jsonAdapter = moshi.adapter(MemoryCard::class.java)
        return Transformations.map(
            db.memoryCardDao().getAll()
        ) { entityList ->
            entityList.map { entity -> jsonAdapter.fromJson(entity.cardJson!!)!! }
        }
    }

    fun getCard(uuid: String): LiveData<MemoryCard> {
        val jsonAdapter = moshi.adapter(MemoryCard::class.java)
        return Transformations.map(
            db.memoryCardDao().getByUuid(uuid)
        ) { entity ->
            jsonAdapter.fromJson(entity.cardJson!!)!!
        }
    }


    fun getCardsAsFlowable(): Flow<List<MemoryCard>> {
        TODO()
    }

    suspend fun deleteCard(card: MemoryCard) {
        db.memoryCardDao().deleteByUid(card.uuid)
    }
}

package com.citizenwarwick.memset.core

import android.util.Log
import com.citizenwarwick.memset.core.di.get
import com.citizenwarwick.memset.core.model.MemoryCard
import com.squareup.moshi.Moshi

class MemoryCardRepository(private val moshi: Moshi = get()) {
    fun saveCard(card: MemoryCard) {
        val jsonAdapter = moshi.adapter(MemoryCard::class.java)
        val json = jsonAdapter.toJson(card)
        Log.d("memsetlog", json)
    }
}

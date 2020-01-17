package com.citizenwarwick.memset.core

import android.util.Log
import com.citizenwarwick.memset.core.model.MemoryCard
import com.citizenwarwick.memset.core.model.adapters.ColorAdapter
import com.citizenwarwick.memset.core.model.adapters.MemoryCardElementAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MemoryCardRepository {
    fun saveCard(card: MemoryCard) {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(ColorAdapter())
            .add(MemoryCardElementAdapter())
            .build()
        val jsonAdapter = moshi.adapter(MemoryCard::class.java)
        val json = jsonAdapter.toJson(card)
        Log.d("memsetlog", json)
    }
}

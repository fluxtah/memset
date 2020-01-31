package com.citizenwarwick.memset.core.model.adapters

import androidx.compose.frames.ModelList
import androidx.compose.frames.modelListOf
import com.citizenwarwick.memset.core.model.MemoryCardElement
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class MemoryCardElementModelListAdapter {
    @ToJson
    fun arrayListToJson(list: ModelList<MemoryCardElement>): List<MemoryCardElement> = list

    @FromJson
    fun arrayListFromJson(list: List<MemoryCardElement>): ModelList<MemoryCardElement> =
        modelListOf(*list.toTypedArray())
}

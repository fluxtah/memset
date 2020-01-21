package com.citizenwarwick.memset.features.home.model

import com.citizenwarwick.memset.core.model.MemoryCard

interface HomeScreenModel {
    val state: HomeScreenState

    fun deleteCard(card: MemoryCard)
}

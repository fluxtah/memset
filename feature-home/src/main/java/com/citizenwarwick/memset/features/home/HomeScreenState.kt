package com.citizenwarwick.memset.features.home

import androidx.compose.Model
import androidx.compose.frames.ModelList
import androidx.compose.frames.modelListOf
import com.citizenwarwick.memset.core.model.LoadingState
import com.citizenwarwick.memset.core.model.MemoryCard

@Model
data class HomeScreenState(
    var loadingState: LoadingState = LoadingState.Loading,
    var cards: ModelList<MemoryCard> = modelListOf()
)

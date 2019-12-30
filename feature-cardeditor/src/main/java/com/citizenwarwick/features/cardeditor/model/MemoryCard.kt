package com.citizenwarwick.features.cardeditor.model

import androidx.compose.Model

@Model
data class MemoryCard(
    val frontElements: List<MemoryCardElement>,
    val backElements: List<MemoryCardElement>
)


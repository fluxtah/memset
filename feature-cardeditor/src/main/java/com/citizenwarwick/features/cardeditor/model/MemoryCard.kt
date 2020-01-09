package com.citizenwarwick.features.cardeditor.model

import androidx.compose.Model
import androidx.compose.frames.ModelMap
import androidx.compose.frames.modelMapOf

@Model
data class MemoryCard(
    val front: CardSurface,
    val back: CardSurface,
    val facingFront: Boolean = true
) {
    val upSide: CardSurface
        get() = if (facingFront) front else back
}

@Model
data class CardSurface(
    val properties: ModelMap<String, String> = modelMapOf(),
    val elements: List<MemoryCardElement>
)

@Model
data class MemoryCardElement(val type: String, val properties: ModelMap<String, String> = modelMapOf())

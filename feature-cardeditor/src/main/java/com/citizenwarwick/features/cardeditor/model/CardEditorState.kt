package com.citizenwarwick.features.cardeditor.model

import androidx.compose.Model

@Model
data class CardEditorState(
    var loadingState: LoadingState,
    var card: MemoryCard,
    var selectedElement: MemoryCardElement? = null
)

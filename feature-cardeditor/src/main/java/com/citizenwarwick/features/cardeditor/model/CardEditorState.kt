package com.citizenwarwick.features.cardeditor.model

import androidx.compose.Model
import com.citizenwarwick.features.cardeditor.LoadingState

@Model
data class CardEditorState(
    var loadingState: LoadingState,
    var card: MemoryCard,
    var selectedElement: MemoryCardElement? = null
)

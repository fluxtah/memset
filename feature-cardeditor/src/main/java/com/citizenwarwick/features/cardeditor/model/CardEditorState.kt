package com.citizenwarwick.features.cardeditor.model

import androidx.compose.Model
import com.citizenwarwick.features.cardeditor.LoadingState
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration

@Model
data class CardEditorState(
    val editorConfiguration: EditorConfiguration = EditorConfiguration(),
    var loadingState: LoadingState,
    var card: MemoryCard,
    var selectedElement: MemoryCardElement? = null
)

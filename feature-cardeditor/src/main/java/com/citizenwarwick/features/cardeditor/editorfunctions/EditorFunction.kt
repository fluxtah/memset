package com.citizenwarwick.features.cardeditor.editorfunctions

import com.citizenwarwick.features.cardeditor.model.CardEditorState
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

interface EditorFunction {
    fun apply(
        editorState: CardEditorState,
        frontElements: MutableList<MemoryCardElement>,
        backElements: MutableList<MemoryCardElement>
    )
}

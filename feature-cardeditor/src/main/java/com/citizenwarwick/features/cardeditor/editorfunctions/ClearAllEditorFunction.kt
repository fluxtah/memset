package com.citizenwarwick.features.cardeditor.editorfunctions

import com.citizenwarwick.features.cardeditor.model.CardEditorState
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

class ClearAllEditorFunction : EditorFunction {
    override fun apply(
        editorState: CardEditorState,
        frontElements: MutableList<MemoryCardElement>,
        backElements: MutableList<MemoryCardElement>
    ) {
        editorState.selectedElement = null
        frontElements.clear()
        backElements.clear()
    }
}

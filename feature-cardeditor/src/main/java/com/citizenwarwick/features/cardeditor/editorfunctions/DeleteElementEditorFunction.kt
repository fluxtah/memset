package com.citizenwarwick.features.cardeditor.editorfunctions

import com.citizenwarwick.features.cardeditor.model.CardEditorState
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

class DeleteElementEditorFunction : EditorFunction {
    override fun apply(
        editorState: CardEditorState,
        frontElements: MutableList<MemoryCardElement>,
        backElements: MutableList<MemoryCardElement>
    ) {
        editorState.selectedElement?.let { element ->
            if (frontElements.remove(element)) {
                editorState.selectedElement = null
            }
            if (backElements.remove(element)) {
                editorState.selectedElement = null
            }
        }
    }
}

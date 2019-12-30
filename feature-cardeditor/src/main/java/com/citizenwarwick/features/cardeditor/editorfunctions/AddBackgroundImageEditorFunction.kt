package com.citizenwarwick.features.cardeditor.editorfunctions

import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.Companion.ELEMENT_TYPE_BG_IMAGE
import com.citizenwarwick.features.cardeditor.model.CardEditorState
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

class AddBackgroundImageEditorFunction : EditorFunction {
    override fun apply(
        editorState: CardEditorState,
        frontElements: MutableList<MemoryCardElement>,
        backElements: MutableList<MemoryCardElement>
    ) {
        frontElements.add(MemoryCardElement(ELEMENT_TYPE_BG_IMAGE))
    }
}

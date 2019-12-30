package com.citizenwarwick.features.cardeditor.editorfunctions

import androidx.ui.core.Alignment
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.Companion.ELEMENT_TYPE_TEXT
import com.citizenwarwick.features.cardeditor.model.CardEditorState
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

class AddTextEditorFunction : EditorFunction {
    override fun apply(
        editorState: CardEditorState,
        frontElements: MutableList<MemoryCardElement>,
        backElements: MutableList<MemoryCardElement>
    ) {
        frontElements.add(MemoryCardElement(ELEMENT_TYPE_TEXT).apply {
            properties["alignment"] = Alignment.Center.name
        })
    }
}

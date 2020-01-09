package com.citizenwarwick.features.cardeditor.editorfunctions

import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.ELEMENT_TYPE_SHAPE_OVAL
import com.citizenwarwick.features.cardeditor.model.CardEditorState
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

class AddOvalShapeEditorFunction : EditorFunction {
    override fun apply(
        editorState: CardEditorState,
        frontElements: MutableList<MemoryCardElement>,
        backElements: MutableList<MemoryCardElement>
    ) {
        frontElements.add(MemoryCardElement(ELEMENT_TYPE_SHAPE_OVAL))
    }
}

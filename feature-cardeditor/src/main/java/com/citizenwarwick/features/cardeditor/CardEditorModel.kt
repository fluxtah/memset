package com.citizenwarwick.features.cardeditor

import com.citizenwarwick.features.cardeditor.config.EditorFunctionConfig
import com.citizenwarwick.features.cardeditor.model.CardEditorState
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

interface CardEditorModel {
    val state: CardEditorState
    fun applyEditorFunction(editorFunction: EditorFunctionConfig)
    fun selectElement(element: MemoryCardElement)
}

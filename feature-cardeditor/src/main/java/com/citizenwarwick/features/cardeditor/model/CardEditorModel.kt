package com.citizenwarwick.features.cardeditor.model

import com.citizenwarwick.features.cardeditor.config.EditorFunctionConfig

interface CardEditorModel {
    val state: CardEditorState
    fun applyEditorFunction(editorFunction: EditorFunctionConfig)
}

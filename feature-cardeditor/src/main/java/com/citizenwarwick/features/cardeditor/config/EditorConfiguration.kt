package com.citizenwarwick.features.cardeditor.config

import androidx.compose.Model
import com.citizenwarwick.features.cardeditor.R

@Model
data class EditorConfiguration(
    val editorFunctions: List<EditorFunctionConfig> = mutableListOf(
        EditorFunctionConfig(FUNC_ADD_TEXT, R.drawable.ic_editor_tool_add_text),
        EditorFunctionConfig(FUNC_ADD_BG_IMAGE, R.drawable.ic_editor_tool_add_background),
        EditorFunctionConfig(FUNC_CLEAR_ALL, R.drawable.ic_editor_tool_clear_all)
    )
) {
    companion object {
        const val FUNC_ADD_TEXT = "add-text"
        const val FUNC_ADD_BG_IMAGE = "add-background-image"
        const val FUNC_CLEAR_ALL = "clear-all"

        const val ELEMENT_TYPE_TEXT = "text"
        const val ELEMENT_TYPE_TEXT_DEFAULT_SIZE_EM = 16.0f
        const val ELEMENT_TYPE_TEXT_DEFAULT_MIN_SIZE_EM = 8.0f
        const val ELEMENT_TYPE_TEXT_DEFAULT_MAX_SIZE_EM = 32.0f

        const val ELEMENT_TYPE_BG_IMAGE = "background-image"

        const val ELEMENT_PROPERTY_TEXT_SIZE = "textSize"
    }
}

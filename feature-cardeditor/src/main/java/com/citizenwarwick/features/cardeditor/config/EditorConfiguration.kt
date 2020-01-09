package com.citizenwarwick.features.cardeditor.config

import com.citizenwarwick.features.cardeditor.R

object EditorConfiguration {
    const val FUNC_ADD_TEXT = "add-text"
    const val FUNC_ADD_SHAPE_OVAL = "add-oval"
    const val FUNC_CLEAR_ALL = "clear-all"
    const val FUNC_DELETE_ELEMENT = "delete-element"

    const val ELEMENT_TYPE_TEXT = "text"
    const val ELEMENT_TYPE_SHAPE_OVAL = "oval"

    /**
     * Constants for the card surface
     */
    const val SURFACE_PROPERTY_COLOR = "color"

    /**
     * Constants for the text element
     */
    const val ELEMENT_TYPE_TEXT_DEFAULT_SIZE_EM = 8.0f
    const val ELEMENT_TYPE_TEXT_DEFAULT_MIN_SIZE_EM = 4.0f
    const val ELEMENT_TYPE_TEXT_DEFAULT_MAX_SIZE_EM = 32.0f
    const val ELEMENT_PROPERTY_TEXT_SIZE = "size"
    const val ELEMENT_PROPERTY_TEXT_ALIGNMENT = "alignment"
    const val ELEMENT_PROPERTY_TEXT_COLOR = "color"
    const val ELEMENT_PROPERTY_TEXT_CONTENT = "text"
    const val ELEMENT_PROPERTY_TEXT_SPACING_LEFT = "spacingLeft"
    const val ELEMENT_PROPERTY_TEXT_SPACING_RIGHT = "spacingRight"
    const val ELEMENT_PROPERTY_TEXT_SPACING_TOP = "spacingTop"
    const val ELEMENT_PROPERTY_TEXT_SPACING_BOTTOM = "spacingBottom"

    /**
     * Constants for the oval element
     */
    const val ELEMENT_PROPERTY_OVAL_COLOR = "color"
    const val ELEMENT_PROPERTY_OVAL_WIDTH = "width"
    const val ELEMENT_PROPERTY_OVAL_HEIGHT = "height"

    val editorFunctions: List<EditorFunctionConfig> = mutableListOf(
        EditorFunctionConfig(FUNC_ADD_TEXT, R.drawable.ic_editor_tool_add_text),
        EditorFunctionConfig(FUNC_ADD_SHAPE_OVAL, R.drawable.ic_editor_tool_add_oval),
        EditorFunctionConfig(FUNC_DELETE_ELEMENT, R.drawable.ic_editor_tool_delete),
        EditorFunctionConfig(FUNC_CLEAR_ALL, R.drawable.ic_editor_tool_clear_all)
    )
}

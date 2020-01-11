/**
 * Copyright 2020 Ian Warwick
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.citizenwarwick.features.cardeditor.config

import com.citizenwarwick.features.cardeditor.R

object EditorConfiguration {
    const val FUNC_ADD_TEXT = "add-text"
    const val FUNC_ADD_SHAPE_OVAL = "add-oval"
    const val FUNC_CLEAR_ALL = "clear-all"
    const val FUNC_DELETE_ELEMENT = "delete-element"

    /**
     * Constants for the text element
     */
    const val ELEMENT_TYPE_TEXT_DEFAULT_MIN_SIZE_EM = 4.0f
    const val ELEMENT_TYPE_TEXT_DEFAULT_MAX_SIZE_EM = 32.0f

    val editorFunctions: List<EditorFunctionConfig> = mutableListOf(
        EditorFunctionConfig(FUNC_ADD_TEXT, R.drawable.ic_editor_tool_add_text),
        EditorFunctionConfig(FUNC_ADD_SHAPE_OVAL, R.drawable.ic_editor_tool_add_oval),
        EditorFunctionConfig(FUNC_DELETE_ELEMENT, R.drawable.ic_editor_tool_delete),
        EditorFunctionConfig(FUNC_CLEAR_ALL, R.drawable.ic_editor_tool_clear_all)
    )
}

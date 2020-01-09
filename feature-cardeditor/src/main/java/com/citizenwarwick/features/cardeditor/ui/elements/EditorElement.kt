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
package com.citizenwarwick.features.cardeditor.ui.elements

import androidx.compose.Composable
import com.citizenwarwick.features.cardeditor.model.CardEditorModel
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun EditorElement(model: CardEditorModel, element: MemoryCardElement) {
    when (element.type) {
        EditorConfiguration.ELEMENT_TYPE_TEXT -> TextElement(
            model,
            element
        )
        EditorConfiguration.ELEMENT_TYPE_SHAPE_OVAL -> OvalShapeElement(
            model,
            element
        )
    }
}

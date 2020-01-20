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
package com.citizenwarwick.ui.card.elements

import androidx.compose.Composable
import androidx.ui.core.gesture.DoubleTapGestureDetector
import androidx.ui.foundation.Clickable
import androidx.ui.layout.Align
import com.citizenwarwick.memset.core.model.MemoryCardElement
import com.citizenwarwick.memset.core.model.ShapeElement
import com.citizenwarwick.memset.core.model.TextElement

@Composable
fun MemoryCardElement(
    element: MemoryCardElement,
    onClick: (MemoryCardElement) -> Unit = {},
    onDoubleTap: (MemoryCardElement) -> Unit = {},
    isSelected: Boolean = false,
    isEditing: Boolean = false
) {
    Align(alignment = element.alignment) {
        Clickable(onClick = { onClick(element) }) {
            DoubleTapGestureDetector(onDoubleTap = { onDoubleTap(element) }) {
                when (element) {
                    is TextElement -> TextElement(
                        element,
                        isSelected,
                        isEditing
                    )
                    is ShapeElement -> ShapeElement(
                        element,
                        isSelected
                    )
                }
            }
        }
    }
}

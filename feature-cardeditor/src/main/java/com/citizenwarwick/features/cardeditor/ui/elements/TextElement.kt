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
import androidx.ui.core.PxPosition
import androidx.ui.core.Text
import androidx.ui.core.TextField
import androidx.ui.core.TextUnit
import androidx.ui.core.dp
import androidx.ui.core.gesture.DoubleTapGestureDetector
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Spacing
import androidx.ui.text.TextStyle
import com.citizenwarwick.features.cardeditor.model.CardEditorModel
import com.citizenwarwick.features.cardeditor.model.TextElement
import com.citizenwarwick.features.cardeditor.ui.SelectionBorder

@Composable
fun TextElement(model: CardEditorModel, element: TextElement) {
    val onTextClick = { model.state.selectedElement = element }
    val onTextDoubleTap = { _: PxPosition -> model.state.editElement = element }

    Clickable(onClick = onTextClick) {
        when {
            model.state.editElement == element -> {
                SelectionBorder { Text(element, true) }
            }
            model.state.selectedElement == element -> {
                SelectionBorder {
                    DoubleTapGestureDetector(onDoubleTap = onTextDoubleTap) {
                        Text(element)
                    }
                }
            }
            else -> {
                DoubleTapGestureDetector(onDoubleTap = onTextDoubleTap) { Text(element) }
            }
        }
    }
}

@Composable
private fun Text(
    element: TextElement,
    inEdit: Boolean = false
) {
    val textStyle = TextStyle(
        background = Color.Transparent,
        fontWeight = element.fontWeight,
        fontSize = TextUnit.Em(element.fontSize),
        color = element.color
    )

    val spacing =
        Spacing(element.spacingLeft.dp, element.spacingTop.dp, element.spacingRight.dp, element.spacingBottom.dp)

    if (inEdit) {
        TextField(modifier = spacing, value = element.text, textStyle = textStyle, onValueChange = {
            element.text = it
        })
    } else {
        Text(modifier = spacing, text = element.text, style = textStyle)
    }
}


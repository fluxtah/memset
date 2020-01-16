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
package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import com.citizenwarwick.memset.core.model.ShapeElement
import com.citizenwarwick.memset.core.model.ShapeType
import com.citizenwarwick.features.cardeditor.ui.elementcontrols.properties.AlignmentPropertyControl
import com.citizenwarwick.features.cardeditor.ui.elementcontrols.properties.ColorPropertyControl
import com.citizenwarwick.features.cardeditor.ui.elementcontrols.properties.DropDownMenuPropertyControl
import com.citizenwarwick.features.cardeditor.ui.elementcontrols.properties.SliderPropertyControl
import com.citizenwarwick.features.cardeditor.ui.elementcontrols.properties.SpacingPropertyControls

@Composable
fun ShapeElementPropertyControls(element: ShapeElement) {
    val spacingValueRange = -64f..128f
    val shapeWidth = element.width
    val shapeHeight = element.height

    Column {

        ShapeTypeDropDownPropertyControl(element)

        ColorPropertyControl(
            "Color",
            selectedColor = element.color,
            onColorSelected = {
                element.color = it
            }
        )

        SliderPropertyControl(
            label = "Width",
            initialValue = shapeWidth,
            valueRange = 8f..512f,
            onValueChanged = {
                element.width = it
            }
        )

        SliderPropertyControl(
            label = "Height",
            initialValue = shapeHeight,
            valueRange = 8f..512f,
            onValueChanged = {
                element.height = it
            }
        )

        AlignmentPropertyControl(
            selectedAlignment = element.alignment,
            onAlignmentSelected = {
                element.alignment = it
            }
        )

        SpacingPropertyControls(element, spacingValueRange)
    }
}

@Composable
private fun ShapeTypeDropDownPropertyControl(element: ShapeElement) {
    val shapeTypes = ModelList<ShapeType>().apply { addAll(
        ShapeType.values()) }

    var isShapeTypeDropDownOpen by +state { false }

    DropDownMenuPropertyControl(
        label = "Shape",
        items = shapeTypes,
        isOpen = isShapeTypeDropDownOpen,
        selectedItem = shapeTypes.find { it == element.shapeType },
        selectedItemLabelText = { it.name },
        onDropDownPressed = { isShapeTypeDropDownOpen = it }) {
        Surface(color = if (element.shapeType == it) Color.LightGray else Color.Transparent) {
            Padding(padding = 2.dp) {
                Ripple(bounded = true) {
                    Clickable(onClick = { element.shapeType = it }) {
                        Container(expanded = true) {
                            Text(modifier = Spacing(4.dp), text = it.name)
                        }
                    }
                }
            }
        }
    }
}

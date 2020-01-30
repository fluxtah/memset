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
package com.citizenwarwick.features.carddesigner.ui.elementcontrols

import androidx.compose.Composable
import androidx.compose.frames.modelListOf
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.dp
import com.citizenwarwick.features.carddesigner.config.EditorConfiguration
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.AlignmentPropertyControl
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.ColorPropertyControl
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.DropDownMenuPropertyControl
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.SliderPropertyControl
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.SpacingPropertyControls
import com.citizenwarwick.memset.core.model.TextElement

@Composable
fun TextElementPropertyControls(element: TextElement) {
    Column {
        val spacingValueRange = -64f..128f

        SliderPropertyControl(
            label = "Text Size",
            initialValue = element.fontSize,
            valueRange = EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_MIN_SIZE_EM..EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_MAX_SIZE_EM,
            onValueChanged = {
                element.fontSize = it
            }
        )
        AlignmentPropertyControl(
            selectedAlignment = element.alignment,
            onAlignmentSelected = {
                element.alignment = it
            }
        )

        ColorPropertyControl(
            label = "Text Color",
            selectedColor = element.color,
            onColorSelected = {
                element.color = it
            }
        )

        FontWeightDropDownPropertyControl(element)

        SpacingPropertyControls(element, spacingValueRange)
    }
}

@Composable
private fun FontWeightDropDownPropertyControl(element: TextElement) {
    val fontWeights = modelListOf(
        "Normal" to FontWeight.Normal,
        "Bold" to FontWeight.Bold
    )

    var isFontWeightDropDownOpen by state { false }

    DropDownMenuPropertyControl(
        label = "Font Weight",
        items = fontWeights,
        isOpen = isFontWeightDropDownOpen,
        selectedItem = fontWeights.find { it.second == element.fontWeight },
        selectedItemLabelText = { it.first },
        onDropDownPressed = { isFontWeightDropDownOpen = it }) {
        Surface(color = if (element.fontWeight == it.second) Color.LightGray else Color.Transparent) {
            Padding(padding = 2.dp) {
                Ripple(bounded = true) {
                    Clickable(onClick = { element.fontWeight = it.second }) {
                        Container(expanded = true) {
                            Text(modifier = Spacing(4.dp), text = it.first)
                        }
                    }
                }
            }
        }
    }
}

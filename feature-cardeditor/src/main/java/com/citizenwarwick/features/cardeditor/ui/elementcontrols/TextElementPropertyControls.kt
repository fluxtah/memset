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
import androidx.compose.frames.modelListOf
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun TextElementPropertyControls(element: MemoryCardElement) {
    Column {
        val textSize = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SIZE]?.toFloat()
            ?: EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_SIZE_EM
        val spacingTop = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_TOP]?.toFloat()
            ?: 0f
        val spacingRight = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_RIGHT]?.toFloat()
            ?: 0f
        val spacingBottom = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_BOTTOM]?.toFloat()
            ?: 0f
        val spacingLeft = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_LEFT]?.toFloat()
            ?: 0f
        val spacingValueRange = -64f..64f

        SliderPropertyControl(
            label = "Text Size",
            initialValue = textSize,
            valueRange = EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_MIN_SIZE_EM..EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_MAX_SIZE_EM,
            onValueChanged = {
                element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SIZE] = it.toString()
            }
        )
        TextAlignmentPropertyControl(element.properties)
        ColorPropertyControl(
            "Text Color",
            element.properties,
            EditorConfiguration.ELEMENT_PROPERTY_TEXT_COLOR,
            defaultColor = Color.Black
        )
        SliderPropertyControl(
            label = "Spacing Top",
            initialValue = spacingTop,
            valueRange = spacingValueRange,
            onValueChanged = {
                element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_TOP] = it.toString()
            }
        )
        SliderPropertyControl(
            label = "Spacing Right",
            initialValue = spacingRight,
            valueRange = spacingValueRange,
            onValueChanged = {
                element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_RIGHT] = it.toString()
            }
        )
        SliderPropertyControl(
            label = "Spacing Bottom",
            initialValue = spacingBottom,
            valueRange = spacingValueRange,
            onValueChanged = {
                element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_BOTTOM] = it.toString()
            }
        )
        SliderPropertyControl(
            label = "Spacing Left",
            initialValue = spacingLeft,
            valueRange = spacingValueRange,
            onValueChanged = {
                element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_LEFT] = it.toString()
            }
        )
    }
}


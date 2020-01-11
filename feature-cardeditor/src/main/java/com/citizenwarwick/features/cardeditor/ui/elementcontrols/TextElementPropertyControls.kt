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
import androidx.ui.layout.Column
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.TextElement

@Composable
fun TextElementPropertyControls(element: TextElement) {
    Column {
        val spacingValueRange = -64f..64f

        SliderPropertyControl(
            label = "Text Size",
            initialValue = element.textSize,
            valueRange = EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_MIN_SIZE_EM..EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_MAX_SIZE_EM,
            onValueChanged = {
                element.textSize = it
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
        SliderPropertyControl(
            label = "Spacing Top",
            initialValue = element.spacingTop,
            valueRange = spacingValueRange,
            onValueChanged = {
                element.spacingTop = it
            }
        )
        SliderPropertyControl(
            label = "Spacing Right",
            initialValue = element.spacingRight,
            valueRange = spacingValueRange,
            onValueChanged = {
                element.spacingRight = it
            }
        )
        SliderPropertyControl(
            label = "Spacing Bottom",
            initialValue = element.spacingBottom,
            valueRange = spacingValueRange,
            onValueChanged = {
                element.spacingBottom = it
            }
        )
        SliderPropertyControl(
            label = "Spacing Left",
            initialValue = element.spacingLeft,
            valueRange = spacingValueRange,
            onValueChanged = {
                element.spacingLeft = it
            }
        )
    }
}


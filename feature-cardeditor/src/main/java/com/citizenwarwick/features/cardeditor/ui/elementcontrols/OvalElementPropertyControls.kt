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
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun OvalElementPropertyControls(element: MemoryCardElement) {
    val ovalWidth = element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_WIDTH]?.toFloat() ?: 64f
    val ovalHeight = element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_HEIGHT]?.toFloat() ?: 64f
    Column {
        ColorPropertyControl(
            "Color",
            element.properties,
            EditorConfiguration.ELEMENT_PROPERTY_OVAL_COLOR,
            defaultColor = Color.Red
        )

        SliderPropertyControl(
            label = "Width",
            initialValue = ovalWidth,
            valueRange = 8f..512f,
            onValueChanged = {
                element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_WIDTH] =
                    it.toString()
            }
        )

        SliderPropertyControl(
            label = "Height",
            initialValue = ovalHeight,
            valueRange = 8f..512f,
            onValueChanged = {
                element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_HEIGHT] =
                    it.toString()
            }
        )
    }
}

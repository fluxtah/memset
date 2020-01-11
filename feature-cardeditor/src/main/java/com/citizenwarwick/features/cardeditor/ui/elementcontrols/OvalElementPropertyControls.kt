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
import com.citizenwarwick.features.cardeditor.model.OvalShapeElement

@Composable
fun OvalElementPropertyControls(element: OvalShapeElement) {
    val ovalWidth = element.width
    val ovalHeight = element.height
    Column {
        ColorPropertyControl(
            "Color",
            selectedColor = element.color,
            onColorSelected = {
                element.color = it
            }
        )

        SliderPropertyControl(
            label = "Width",
            initialValue = ovalWidth,
            valueRange = 8f..512f,
            onValueChanged = {
                element.width = it
            }
        )

        SliderPropertyControl(
            label = "Height",
            initialValue = ovalHeight,
            valueRange = 8f..512f,
            onValueChanged = {
                element.height = it
            }
        )
    }
}

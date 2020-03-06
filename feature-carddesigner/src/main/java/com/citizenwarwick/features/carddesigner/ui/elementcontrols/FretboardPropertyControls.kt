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
import androidx.compose.frames.ModelList
import androidx.compose.state
import androidx.core.math.MathUtils.clamp
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.unit.dp
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.AlignmentPropertyControl
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.DropDownMenuPropertyControl
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.SliderPropertyControl
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.SpacingPropertyControls
import com.citizenwarwick.memset.core.model.FretboardElement

@Composable
fun FretboardPropertyControls(element: FretboardElement) {
    val spacingValueRange = -64f..128f
    val scale = element.scale * 100

    Column {
        FretboardModeDropDownPropertyControl(element)

        SliderPropertyControl(
            label = "Scale",
            initialValue = scale,
            valueRange = 50f..200f,
            onValueChanged = {
                element.scale = (it / 100)
            }
        )

        SliderPropertyControl(
            label = "Start Fret",
            initialValue = element.startFret.toFloat(),
            valueRange = 0f..30f,
            onValueChanged = {
                element.startFret = clamp(it.toInt(), 0, element.endFret)
            }
        )

        SliderPropertyControl(
            label = "End Fret",
            initialValue = element.endFret.toFloat(),
            valueRange = 0f..30f,
            onValueChanged = {
                element.endFret = clamp(it.toInt(), element.startFret, 30)
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
private fun FretboardModeDropDownPropertyControl(element: FretboardElement) {
    val shapeTypes = ModelList<String>().apply {
        add("chord")
        add("scale")
    }

    var isFretboardModeDropDownOpen by state { false }

    DropDownMenuPropertyControl(
        label = "Shape",
        items = shapeTypes,
        isOpen = isFretboardModeDropDownOpen,
        selectedItem = shapeTypes.find { it == element.mode },
        selectedItemLabelText = { it },
        onDropDownPressed = { isFretboardModeDropDownOpen = it }) {
        Surface(
            modifier = LayoutPadding(2.dp),
            color = if (element.mode == it) Color.LightGray else Color.Transparent
        ) {
            Ripple(bounded = true) {
                Clickable(onClick = { element.mode = it }) {
                    Container(expanded = true) {
                        Text(modifier = LayoutPadding(4.dp), text = it)
                    }
                }
            }
        }
    }
}

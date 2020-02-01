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
package com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.Row
import androidx.ui.material.Slider
import androidx.ui.material.SliderPosition
import kotlin.math.roundToInt

@Composable
fun SliderPropertyControl(
    label: String,
    initialValue: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChanged: (value: Float) -> Unit
) {
    Row {
        Text(modifier = LayoutGravity.Center + LayoutFlexible(0.5f), text = label)
        Slider(
            modifier = LayoutGravity.Center + LayoutFlexible(1f),
            position = SliderPosition(initial = initialValue, valueRange = valueRange),
            onValueChange = {
                onValueChanged(it.roundToInt().toFloat())
            })
        Text(modifier = LayoutGravity.Center, text = initialValue.toString())
    }
}

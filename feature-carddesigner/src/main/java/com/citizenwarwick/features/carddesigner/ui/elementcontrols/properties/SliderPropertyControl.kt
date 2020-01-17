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
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.FlexRow
import androidx.ui.material.Slider
import androidx.ui.material.SliderPosition
import androidx.ui.toStringAsFixed
import kotlin.math.roundToInt

@Composable
fun SliderPropertyControl(
    label: String,
    initialValue: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChanged: (value: Float) -> Unit
) {
    FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
        expanded(1f) {
            Text(text = label)
        }
        expanded(2f) {
            Slider(
                position = SliderPosition(initial = initialValue, valueRange = valueRange),
                modifier = ExpandedWidth,
                onValueChange = {
                    onValueChanged(it.roundToInt().toFloat())
                })
        }
        inflexible {
            Text(text = initialValue.toStringAsFixed(1))
        }
    }
}

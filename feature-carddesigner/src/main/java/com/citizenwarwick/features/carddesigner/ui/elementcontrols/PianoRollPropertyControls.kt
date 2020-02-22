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
import androidx.ui.layout.Column
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.AlignmentPropertyControl
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.SliderPropertyControl
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.properties.SpacingPropertyControls
import com.citizenwarwick.memset.core.model.PianoRollElement

@Composable
fun PianoRollPropertyControls(element: PianoRollElement) {
    val spacingValueRange = -64f..128f
    val scale = element.scale * 100

    Column {
        SliderPropertyControl(
            label = "Scale",
            initialValue = scale,
            valueRange = 50f..200f,
            onValueChanged = {
                element.scale = (it / 100)
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

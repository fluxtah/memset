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

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.frames.ModelMap
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import com.citizenwarwick.features.cardeditor.R
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.ELEMENT_PROPERTY_TEXT_ALIGNMENT

@Composable
fun TextAlignmentPropertyControl(
    properties: ModelMap<String, String>
) {
    val textAlignment = properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT]?.let {
        Alignment.valueOf(it)
    } ?: Alignment.Center

    FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
        expanded(1f) {
            Text(text = "Text Alignment")
        }
        inflexible {
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_align_left,
                (textAlignment == Alignment.TopLeft ||
                    textAlignment == Alignment.CenterLeft ||
                    textAlignment == Alignment.BottomLeft)
            ) {
                properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = when (textAlignment) {
                    Alignment.Center, Alignment.CenterRight ->
                        Alignment.CenterLeft.name
                    Alignment.BottomCenter, Alignment.BottomRight ->
                        Alignment.BottomLeft.name
                    Alignment.TopCenter, Alignment.TopRight ->
                        Alignment.TopLeft.name
                    else ->
                        Alignment.CenterLeft.name
                }
            }
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_align_center,
                (textAlignment == Alignment.TopCenter ||
                    textAlignment == Alignment.BottomCenter ||
                    textAlignment == Alignment.Center)
            ) {
                properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = when (textAlignment) {
                    Alignment.CenterRight, Alignment.CenterLeft ->
                        Alignment.Center.name
                    Alignment.BottomRight, Alignment.BottomLeft ->
                        Alignment.BottomCenter.name
                    Alignment.TopRight, Alignment.TopLeft ->
                        Alignment.TopCenter.name
                    else ->
                        Alignment.Center.name
                }
            }
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_align_right,
                (textAlignment == Alignment.TopRight ||
                    textAlignment == Alignment.BottomRight ||
                    textAlignment == Alignment.CenterRight)
            ) {
                properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = when (textAlignment) {
                    Alignment.Center, Alignment.CenterLeft ->
                        Alignment.CenterRight.name
                    Alignment.BottomCenter, Alignment.BottomLeft ->
                        Alignment.BottomRight.name
                    Alignment.TopCenter, Alignment.TopLeft ->
                        Alignment.TopRight.name
                    else ->
                        Alignment.CenterRight.name
                }
            }
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_vertical_align_bottom,
                (textAlignment == Alignment.BottomRight ||
                    textAlignment == Alignment.BottomCenter ||
                    textAlignment == Alignment.BottomLeft)
            ) {
                properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = when (textAlignment) {
                    Alignment.Center, Alignment.TopCenter ->
                        Alignment.BottomCenter.name
                    Alignment.CenterLeft, Alignment.TopLeft ->
                        Alignment.BottomLeft.name
                    Alignment.CenterRight, Alignment.TopRight ->
                        Alignment.BottomRight.name
                    else ->
                        Alignment.BottomCenter.name
                }
            }
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_vertical_align_center,
                (textAlignment == Alignment.Center ||
                    textAlignment == Alignment.CenterLeft ||
                    textAlignment == Alignment.CenterRight)
            ) {
                properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = when (textAlignment) {
                    Alignment.BottomCenter, Alignment.TopCenter ->
                        Alignment.Center.name
                    Alignment.TopLeft, Alignment.BottomLeft ->
                        Alignment.CenterLeft.name
                    Alignment.TopRight, Alignment.BottomRight ->
                        Alignment.CenterRight.name
                    else ->
                        Alignment.Center.name
                }
            }
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_vertical_align_top,
                (textAlignment == Alignment.TopRight ||
                    textAlignment == Alignment.TopCenter ||
                    textAlignment == Alignment.TopLeft)
            ) {
                properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = when (textAlignment) {
                    Alignment.Center, Alignment.BottomCenter ->
                        Alignment.TopCenter.name
                    Alignment.CenterRight, Alignment.BottomRight ->
                        Alignment.TopRight.name
                    Alignment.CenterLeft, Alignment.BottomLeft ->
                        Alignment.TopLeft.name
                    else ->
                        Alignment.TopCenter.name
                }
            }
        }
    }
}

@Composable
private fun EditorControlOptionButton(@DrawableRes vectorResourceId: Int, selected: Boolean, onClick: () -> Unit) {
    val vector = +vectorResource(vectorResourceId)
    val colors = (+MaterialTheme.colors())
    val selectedColor = if (selected) {
        colors.primary
    } else {
        Color.Transparent
    }
    val selectedOnColor = if (selected) {
        colors.onPrimary
    } else {
        Color.Transparent
    }
    Surface(color = selectedColor, shape = RoundedCornerShape(4.dp), modifier = Spacing(2.dp)) {
        Clickable(onClick = onClick) {
            Container(width = 32.dp, height = 32.dp) {
                DrawVector(vectorImage = vector, tintColor = selectedOnColor)
            }
        }
    }
}

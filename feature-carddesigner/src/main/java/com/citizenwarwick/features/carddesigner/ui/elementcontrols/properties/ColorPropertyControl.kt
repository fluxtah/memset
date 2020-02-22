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
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.foundation.Border
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.Color.Companion.Black
import androidx.ui.graphics.Color.Companion.Blue
import androidx.ui.graphics.Color.Companion.Cyan
import androidx.ui.graphics.Color.Companion.Gray
import androidx.ui.graphics.Color.Companion.Green
import androidx.ui.graphics.Color.Companion.Red
import androidx.ui.graphics.Color.Companion.White
import androidx.ui.graphics.Color.Companion.Yellow
import androidx.ui.graphics.Shape
import androidx.ui.graphics.lerp
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Row
import androidx.ui.material.surface.Surface
import androidx.ui.unit.Dp
import androidx.ui.unit.dp

@Composable
fun ColorPropertyControl(
    label: String,
    selectedColor: Color = White,
    onColorSelected: (Color) -> Unit
) {
    Row {
        Text(modifier = LayoutGravity.Center + LayoutFlexible(1f), text = label)
        listOf(Black, Red, Yellow, Blue, Green, Cyan, Gray, White).forEach { color ->
            ColorOptionButton(
                modifier = LayoutGravity.Center,
                color = color,
                selected = selectedColor == color
            ) { selectedColor ->
                onColorSelected(selectedColor)
            }
        }
    }
}

@Composable
fun ColorOptionButton(
    modifier: Modifier = Modifier.None,
    shape: Shape = RoundedCornerShape(4.dp),
    width: Dp = 32.dp,
    height: Dp = 32.dp,
    borderSize: Dp = 2.dp,
    color: Color,
    selected: Boolean,
    onClick: (selectedColor: Color) -> Unit
) {
    Surface(
        modifier = LayoutPadding(2.dp) + modifier,
        shape = shape,
        color = color.let { if (selected) lerp(it, White, 0.1f) else it },
        elevation = if (selected) 6.dp else 0.dp,
        border = Border(if (selected) 0.dp else borderSize, Color.LightGray)
    ) {
        Clickable(onClick = {
            onClick(color)
        }) {
            Container(width = width, height = height) {}
        }
    }
}


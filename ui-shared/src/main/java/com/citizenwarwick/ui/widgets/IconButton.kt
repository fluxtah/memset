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
package com.citizenwarwick.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp

@Composable
fun IconButton(
    modifier: Modifier = Modifier.None,
    @DrawableRes vectorResourceId: Int,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    val vector = vectorResource(vectorResourceId)
    IconButton(modifier, vector, selected, onClick)
}

@Composable
fun IconButton(
    modifier: Modifier = Modifier.None,
    iconVector: VectorAsset,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colors()
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
    Surface(
        color = selectedColor,
        shape = RoundedCornerShape(4.dp),
        modifier = modifier + LayoutPadding(2.dp)
    ) {
        Ripple(bounded = true) {
            Clickable(onClick = onClick) {
                Container(width = 32.dp, height = 32.dp) {
                    DrawVector(
                        vectorImage = iconVector,
                        tintColor = selectedOnColor
                    )
                }
            }
        }
    }
}

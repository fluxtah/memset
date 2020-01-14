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
package com.citizenwarwick.features.cardeditor.ui.elements

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Density
import androidx.ui.core.Dp
import androidx.ui.core.Modifier
import androidx.ui.core.dp
import androidx.ui.core.withDensity
import androidx.ui.engine.geometry.Rect
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.GenericShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.Size
import androidx.ui.layout.Spacing
import androidx.ui.material.surface.Surface
import com.citizenwarwick.features.cardeditor.model.CardEditorModel
import com.citizenwarwick.features.cardeditor.model.OvalShapeElement
import com.citizenwarwick.features.cardeditor.ui.SelectionBorder

@Composable
fun OvalShapeElement(model: CardEditorModel, element: OvalShapeElement) {
    val spacing =
        Spacing(element.spacingLeft.dp, element.spacingTop.dp, element.spacingRight.dp, element.spacingBottom.dp)

    Clickable(onClick = { model.state.selectedElement = element }) {
        if (model.state.selectedElement == element) {
            SelectionBorder {
                Oval(
                    modifier = spacing,
                    ovalWidth = element.width.dp,
                    ovalHeight = element.height.dp,
                    color = element.color
                )
            }
        } else {
            Oval(
                modifier = spacing,
                ovalWidth = element.width.dp,
                ovalHeight = element.height.dp,
                color = element.color
            )
        }
    }
}

@Composable
private fun Oval(
    modifier: Modifier,
    ovalWidth: Dp,
    ovalHeight: Dp,
    color: Color
) {
    val density = Density(+ambient(ContextAmbient))
    val widthPx = +withDensity(density) { ovalWidth.toPx().value }
    val heightPx = +withDensity(density) { ovalHeight.toPx().value }

    Container(modifier = modifier, width = ovalWidth, height = ovalHeight, expanded = true) {
        Surface(color = Color.Transparent, modifier = Size(ovalWidth, ovalHeight)) {
            DrawShape(shape = newOvalShape(widthPx, heightPx), color = color)
        }
    }
}

fun newOvalShape(widthPx: Float, heightPx: Float) = GenericShape {
    addOval(Rect(0f, 0f, widthPx, heightPx))
}

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
package com.citizenwarwick.ui.card.elements

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Density
import androidx.ui.core.Dp
import androidx.ui.core.Modifier
import androidx.ui.core.dp
import androidx.ui.core.withDensity
import androidx.ui.engine.geometry.Offset
import androidx.ui.engine.geometry.Rect
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.GenericShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.Size
import androidx.ui.layout.Spacing
import androidx.ui.material.surface.Surface
import com.citizenwarwick.memset.core.model.ShapeElement
import com.citizenwarwick.memset.core.model.ShapeType
import com.citizenwarwick.ui.card.SelectionBorder

@Composable
fun ShapeElement(element: ShapeElement, isSelected: Boolean = false) {
    val spacing =
        Spacing(element.spacingLeft.dp, element.spacingTop.dp, element.spacingRight.dp, element.spacingBottom.dp)

    if (isSelected) {
        SelectionBorder {
            Shape(modifier = spacing, element = element)
        }
    } else {
        Shape(modifier = spacing, element = element)
    }
}

@Composable
private fun Shape(modifier: Modifier, element: ShapeElement) {
    when (element.shapeType) {
        ShapeType.Oval -> {
            OvalShape(
                modifier = modifier,
                ovalWidth = element.width.dp,
                ovalHeight = element.height.dp,
                color = element.color
            )
        }
        ShapeType.Rectangle -> {
            RectangleShape(
                modifier = modifier,
                ovalWidth = element.width.dp,
                ovalHeight = element.height.dp,
                color = element.color
            )
        }
        // UNDONE waiting for polygon path support
//        ShapeType.Triangle -> {
//            TriangleShape(
//                modifier = modifier,
//                ovalWidth = element.width.dp,
//                ovalHeight = element.height.dp,
//                color = element.color
//            )
//        }
    }
}

@Composable
private fun OvalShape(
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
            DrawShape(shape = GenericShape {
                addOval(Rect(0f, 0f, widthPx, heightPx))
            }, color = color)
        }
    }
}

@Composable
private fun RectangleShape(
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
            DrawShape(shape = GenericShape {
                addRect(Rect(0f, 0f, widthPx, heightPx))
            }, color = color)
        }
    }
}

@Composable
private fun TriangleShape(
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
            DrawShape(shape = GenericShape {
                addPolygon(
                    listOf(
                        Offset(0f, heightPx),
                        Offset(widthPx / 2, 0f),
                        Offset(widthPx, heightPx)
                    ), true
                )
            }, color = color)
        }
    }
}

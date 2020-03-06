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
import androidx.ui.core.DensityAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.shape.GenericShape
import androidx.ui.geometry.Rect
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutSize
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import com.citizenwarwick.memset.core.model.ShapeElement
import com.citizenwarwick.memset.core.model.ShapeType
import com.citizenwarwick.ui.card.SelectionBorder

@Composable
fun ShapeElement(element: ShapeElement, isSelected: Boolean = false) {
    val spacing =
        LayoutPadding(element.spacingLeft.dp, element.spacingTop.dp, element.spacingRight.dp, element.spacingBottom.dp)

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
    val density = DensityAmbient.current
    with(density) {
        val widthPx = ovalWidth.toPx().value
        val heightPx = ovalHeight.toPx().value

        Container(modifier = modifier, width = ovalWidth, height = ovalHeight, expanded = true) {
            Box(modifier = LayoutSize(ovalWidth, ovalHeight),
                backgroundColor = color,
                shape = GenericShape {
                    addOval(Rect(0f, 0f, widthPx, heightPx))
                })
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
    val density = DensityAmbient.current
    with(density) {
        val widthPx = ovalWidth.toPx().value
        val heightPx = ovalHeight.toPx().value

        Container(modifier = modifier, width = ovalWidth, height = ovalHeight, expanded = true) {
            Box(backgroundColor = color,
                modifier = LayoutSize(ovalWidth, ovalHeight),
                shape = GenericShape {
                    addRect(Rect(0f, 0f, widthPx, heightPx))
                })
        }
    }
}

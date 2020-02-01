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
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.Row
import com.citizenwarwick.features.carddesigner.R
import com.citizenwarwick.ui.widgets.IconButton

@Composable
fun AlignmentPropertyControl(
    selectedAlignment: Alignment,
    onAlignmentSelected: (Alignment) -> Unit
) {
    Row {
        Text(modifier = LayoutGravity.Center + LayoutFlexible(1f), text = "Alignment")
        IconButton(
            modifier = LayoutGravity.Center,
            vectorResourceId = R.drawable.ic_editor_tool_align_left,
            selected = (selectedAlignment == Alignment.TopLeft ||
                selectedAlignment == Alignment.CenterLeft ||
                selectedAlignment == Alignment.BottomLeft)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.Center, Alignment.CenterRight ->
                        Alignment.CenterLeft
                    Alignment.BottomCenter, Alignment.BottomRight ->
                        Alignment.BottomLeft
                    Alignment.TopCenter, Alignment.TopRight ->
                        Alignment.TopLeft
                    else ->
                        Alignment.CenterLeft
                }
            )
        }
        IconButton(
            modifier = LayoutGravity.Center,
            vectorResourceId = R.drawable.ic_editor_tool_align_center,
            selected = (selectedAlignment == Alignment.TopCenter ||
                selectedAlignment == Alignment.BottomCenter ||
                selectedAlignment == Alignment.Center)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.CenterRight, Alignment.CenterLeft ->
                        Alignment.Center
                    Alignment.BottomRight, Alignment.BottomLeft ->
                        Alignment.BottomCenter
                    Alignment.TopRight, Alignment.TopLeft ->
                        Alignment.TopCenter
                    else ->
                        Alignment.Center
                }
            )
        }
        IconButton(
            modifier = LayoutGravity.Center,
            vectorResourceId = R.drawable.ic_editor_tool_align_right,
            selected = (selectedAlignment == Alignment.TopRight ||
                selectedAlignment == Alignment.BottomRight ||
                selectedAlignment == Alignment.CenterRight)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.Center, Alignment.CenterLeft ->
                        Alignment.CenterRight
                    Alignment.BottomCenter, Alignment.BottomLeft ->
                        Alignment.BottomRight
                    Alignment.TopCenter, Alignment.TopLeft ->
                        Alignment.TopRight
                    else ->
                        Alignment.CenterRight
                }
            )
        }
        IconButton(
            modifier = LayoutGravity.Center,
            vectorResourceId = R.drawable.ic_editor_tool_vertical_align_bottom,
            selected = (selectedAlignment == Alignment.BottomRight ||
                selectedAlignment == Alignment.BottomCenter ||
                selectedAlignment == Alignment.BottomLeft)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.Center, Alignment.TopCenter ->
                        Alignment.BottomCenter
                    Alignment.CenterLeft, Alignment.TopLeft ->
                        Alignment.BottomLeft
                    Alignment.CenterRight, Alignment.TopRight ->
                        Alignment.BottomRight
                    else ->
                        Alignment.BottomCenter
                }
            )
        }
        IconButton(
            modifier = LayoutGravity.Center,
            vectorResourceId = R.drawable.ic_editor_tool_vertical_align_center,
            selected = (selectedAlignment == Alignment.Center ||
                selectedAlignment == Alignment.CenterLeft ||
                selectedAlignment == Alignment.CenterRight)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.BottomCenter, Alignment.TopCenter ->
                        Alignment.Center
                    Alignment.TopLeft, Alignment.BottomLeft ->
                        Alignment.CenterLeft
                    Alignment.TopRight, Alignment.BottomRight ->
                        Alignment.CenterRight
                    else ->
                        Alignment.Center
                }
            )
        }
        IconButton(
            modifier = LayoutGravity.Center,
            vectorResourceId = R.drawable.ic_editor_tool_vertical_align_top,
            selected = (selectedAlignment == Alignment.TopRight ||
                selectedAlignment == Alignment.TopCenter ||
                selectedAlignment == Alignment.TopLeft)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.Center, Alignment.BottomCenter ->
                        Alignment.TopCenter
                    Alignment.CenterRight, Alignment.BottomRight ->
                        Alignment.TopRight
                    Alignment.CenterLeft, Alignment.BottomLeft ->
                        Alignment.TopLeft
                    else ->
                        Alignment.TopCenter
                }
            )
        }
    }
}

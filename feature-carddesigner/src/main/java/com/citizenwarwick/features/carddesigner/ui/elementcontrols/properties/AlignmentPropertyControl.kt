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
            selected = (selectedAlignment == Alignment.TopStart ||
                selectedAlignment == Alignment.CenterStart ||
                selectedAlignment == Alignment.BottomStart)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.Center, Alignment.CenterEnd ->
                        Alignment.CenterStart
                    Alignment.BottomCenter, Alignment.BottomEnd ->
                        Alignment.BottomStart
                    Alignment.TopCenter, Alignment.TopEnd ->
                        Alignment.TopStart
                    else ->
                        Alignment.CenterStart
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
                    Alignment.CenterEnd, Alignment.CenterStart ->
                        Alignment.Center
                    Alignment.BottomEnd, Alignment.BottomStart ->
                        Alignment.BottomCenter
                    Alignment.TopEnd, Alignment.TopStart ->
                        Alignment.TopCenter
                    else ->
                        Alignment.Center
                }
            )
        }
        IconButton(
            modifier = LayoutGravity.Center,
            vectorResourceId = R.drawable.ic_editor_tool_align_right,
            selected = (selectedAlignment == Alignment.TopEnd ||
                selectedAlignment == Alignment.BottomEnd ||
                selectedAlignment == Alignment.CenterEnd)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.Center, Alignment.CenterStart ->
                        Alignment.CenterEnd
                    Alignment.BottomCenter, Alignment.BottomStart ->
                        Alignment.BottomEnd
                    Alignment.TopCenter, Alignment.TopStart ->
                        Alignment.TopEnd
                    else ->
                        Alignment.CenterEnd
                }
            )
        }
        IconButton(
            modifier = LayoutGravity.Center,
            vectorResourceId = R.drawable.ic_editor_tool_vertical_align_bottom,
            selected = (selectedAlignment == Alignment.BottomEnd ||
                selectedAlignment == Alignment.BottomCenter ||
                selectedAlignment == Alignment.BottomStart)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.Center, Alignment.TopCenter ->
                        Alignment.BottomCenter
                    Alignment.CenterStart, Alignment.TopStart ->
                        Alignment.BottomStart
                    Alignment.CenterEnd, Alignment.TopEnd ->
                        Alignment.BottomEnd
                    else ->
                        Alignment.BottomCenter
                }
            )
        }
        IconButton(
            modifier = LayoutGravity.Center,
            vectorResourceId = R.drawable.ic_editor_tool_vertical_align_center,
            selected = (selectedAlignment == Alignment.Center ||
                selectedAlignment == Alignment.CenterStart ||
                selectedAlignment == Alignment.CenterEnd)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.BottomCenter, Alignment.TopCenter ->
                        Alignment.Center
                    Alignment.TopStart, Alignment.BottomStart ->
                        Alignment.CenterStart
                    Alignment.TopEnd, Alignment.BottomEnd ->
                        Alignment.CenterEnd
                    else ->
                        Alignment.Center
                }
            )
        }
        IconButton(
            modifier = LayoutGravity.Center,
            vectorResourceId = R.drawable.ic_editor_tool_vertical_align_top,
            selected = (selectedAlignment == Alignment.TopEnd ||
                selectedAlignment == Alignment.TopCenter ||
                selectedAlignment == Alignment.TopStart)
        ) {
            onAlignmentSelected(
                when (selectedAlignment) {
                    Alignment.Center, Alignment.BottomCenter ->
                        Alignment.TopCenter
                    Alignment.CenterEnd, Alignment.BottomEnd ->
                        Alignment.TopEnd
                    Alignment.CenterStart, Alignment.BottomStart ->
                        Alignment.TopStart
                    else ->
                        Alignment.TopCenter
                }
            )
        }
    }
}

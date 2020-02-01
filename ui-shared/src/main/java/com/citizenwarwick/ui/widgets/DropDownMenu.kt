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

import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.ui.core.DropDownAlignment
import androidx.ui.core.DropdownPopup
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.SolidColor
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Row
import androidx.ui.material.surface.Card
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.citizenwarwick.ui.R

@Composable
fun <T> DropDownMenu(
    items: ModelList<T>,
    selectedItem: T? = null,
    selectedItemLabelText: (T) -> String = { it.toString() },
    isOpen: Boolean,
    onDropDownPressed: (isOpen: Boolean) -> Unit,
    itemTemplate: @Composable() (data: T) -> Unit
) {
    Container {
        Clickable(onClick = { onDropDownPressed(!isOpen) }) {
            DropDownBoxLabel(
                isOpen = isOpen,
                label = if (selectedItem != null && items.contains(selectedItem))
                    selectedItemLabelText(selectedItem)
                else ""
            )
        }

        if (isOpen) {
            DropDownPopupMenu(items = items, itemTemplate = itemTemplate)
        }
    }
}

@Composable
fun <T> DropDownPopupMenu(
    modifier: Modifier = Modifier.None,
    items: ModelList<T>,
    itemTemplate: @Composable() (data: T) -> Unit
) {
    DropdownPopup(dropDownAlignment = DropDownAlignment.Left) {
        Card(
            shape = RoundedCornerShape(4.dp),
            borderWidth = 1.dp,
            modifier = modifier,
            borderBrush = SolidColor(Color.LightGray),
            elevation = 4.dp
        ) {
            DropDownContent(items, itemTemplate)
        }
    }
}

@Composable
private fun <T> DropDownContent(items: ModelList<T>, itemTemplate: @Composable() (data: T) -> Unit) {
    VerticalScroller {
        Column {
            items.forEach {
                itemTemplate(it)
            }
        }
    }
}

@Composable
private fun DropDownBoxLabel(isOpen: Boolean, label: String = "") {
    Container {
        Surface(
            color = Color.White,
            borderWidth = 1.dp,
            elevation = if (isOpen) 0.dp else 2.dp,
            borderBrush = SolidColor(Color.LightGray),
            shape = RoundedCornerShape(4.dp)
        ) {
            Row(modifier = LayoutWidth.Fill) {
                Text(modifier = LayoutGravity.Center + LayoutPadding(8.dp) + LayoutFlexible(1f), text = label)
                Container(modifier = LayoutGravity.Center + LayoutSize(32.dp)) {
                    DrawVector(
                        vectorImage = if (isOpen) {
                            vectorResource(R.drawable.ic_arrow_drop_up)
                        } else {
                            vectorResource(R.drawable.ic_arrow_drop_down)
                        }
                    )
                }
            }
        }
    }
}

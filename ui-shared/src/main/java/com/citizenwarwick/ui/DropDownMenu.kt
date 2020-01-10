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
package com.citizenwarwick.ui

import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.DropDownAlignment
import androidx.ui.core.DropdownPopup
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.FlexRow
import androidx.ui.layout.MaxHeight
import androidx.ui.layout.Spacing
import androidx.ui.material.surface.Card
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource

@Composable
fun <T> DropDownMenu(
    items: ModelList<T>,
    selectedItem: T? = null,
    selectedItemLabelText: (T) -> String = { it.toString() },
    itemTemplate: @Composable() (data: T) -> Unit
) {
    Container {
        var isOpen by +state { false }
        Clickable(onClick = { isOpen = !isOpen }) {
            DropDownBoxLabel(
                isOpen = isOpen,
                label = if (selectedItem != null && items.contains(selectedItem))
                    selectedItemLabelText(selectedItem)
                else ""
            )
        }

        if (isOpen) {
            DropdownPopup(dropDownAlignment = DropDownAlignment.Left) {
                Container {
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        border = Border(color = Color.LightGray, width = 1.dp),
                        modifier = ExpandedWidth,
                        elevation = 4.dp
                    ) {
                        DropDownContent(items, itemTemplate)
                    }
                }
            }
        }
    }
}

@Composable
private fun <T> DropDownContent(
    items: ModelList<T>,
    itemTemplate: @Composable() (data: T) -> Unit
) {
    VerticalScroller(modifier = MaxHeight(196.dp)) {
        Column {
            items.forEach {
                itemTemplate(it)
            }
        }
    }
}

@Composable
private fun DropDownBoxLabel(isOpen: Boolean, label: String = "") {
    if (isOpen) {
        Container {
            Surface(
                color = Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            ) {
                FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
                    expanded(1f) {
                        Text(modifier = Spacing(8.dp), text = label)
                    }
                    inflexible {
                        Container(width = 32.dp, height = 32.dp) {
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_arrow_drop_up))
                        }
                    }
                }
            }
        }
    } else {
        Container {
            Surface(
                color = Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            ) {
                FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
                    expanded(1f) {
                        Text(modifier = Spacing(8.dp), text = label)
                    }
                    inflexible {
                        Container(width = 32.dp, height = 32.dp) {
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_arrow_drop_down))
                        }
                    }
                }
            }
        }
    }
}

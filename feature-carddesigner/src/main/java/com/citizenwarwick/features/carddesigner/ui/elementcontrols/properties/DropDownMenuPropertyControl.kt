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
import androidx.compose.frames.ModelList
import androidx.ui.core.Text
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.FlexRow
import androidx.ui.layout.LayoutPadding
import androidx.ui.unit.dp
import com.citizenwarwick.ui.widgets.DropDownMenu

@Composable
fun <T> DropDownMenuPropertyControl(
    label: String,
    items: ModelList<T>,
    selectedItem: T? = null,
    selectedItemLabelText: (T) -> String = { it.toString() },
    isOpen: Boolean,
    onDropDownPressed: (isOpen: Boolean) -> Unit,
    itemTemplate: @Composable() (data: T) -> Unit
) {
    FlexRow(modifier = LayoutPadding(top = 4.dp, bottom = 4.dp), crossAxisAlignment = CrossAxisAlignment.Center) {
        expanded(1f) {
            Text(text = label)
        }
        expanded(2f) {
            DropDownMenu(
                items,
                selectedItem,
                selectedItemLabelText,
                isOpen,
                onDropDownPressed,
                itemTemplate
            )
        }
    }
}

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
package com.citizenwarwick.features.cardeditor

import MemsetMainTemplate
import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Arrangement
import androidx.ui.layout.AspectRatio
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Padding
import androidx.ui.layout.Row
import androidx.ui.layout.Spacing
import androidx.ui.layout.Stack
import androidx.ui.material.Divider
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.SURFACE_PROPERTY_COLOR
import com.citizenwarwick.features.cardeditor.config.EditorFunctionConfig
import com.citizenwarwick.features.cardeditor.model.CardEditorModel
import com.citizenwarwick.features.cardeditor.model.LoadingState
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement
import com.citizenwarwick.features.cardeditor.ui.elementcontrols.ElementControls
import com.citizenwarwick.features.cardeditor.ui.elements.EditorElement
import com.citizenwarwick.memset.router.Composer
import com.citizenwarwick.ui.DropDownMenu
import com.citizenwarwick.ui.IconButton

class CardEditorScreenComposer(
    private val model: CardEditorModel
) : Composer() {
    override fun compose() {
        MemsetMainTemplate {
            when (model.state.loadingState) {
                LoadingState.Loaded -> CardEditorContent()
                LoadingState.Loading -> CardEditorLoading()
                LoadingState.Error -> CardEditorLoadingError()
            }
        }
    }

    @Composable
    private fun CardEditorContent() {
        Container(alignment = Alignment.TopCenter, padding = EdgeInsets(16.dp)) {
            Column {
                EditorToolbar()
                EditorSurface()
                Divider(height = 8.dp, color = Color.Transparent)
                LayersDropDown()
                Divider(height = 8.dp, color = Color.Transparent)
                VerticalScroller {
                    ElementControls(model)
                }
            }
        }
    }

    @Composable
    private fun EditorSurface() {
        val selectedColor =
            model.state.card.upSide.properties[SURFACE_PROPERTY_COLOR]?.toInt()?.let { Color(it) } ?: Color.White

        Clickable(onClick = {
            val editElement = model.state.editElement
            model.state.editElement = null
            if (editElement == null) {
                model.state.selectedElement = null
            }
        }) {
            Card(
                shape = RoundedCornerShape(4.dp),
                elevation = 4.dp,
                modifier = AspectRatio(1.7f) wraps ExpandedWidth,
                color = selectedColor
            ) {
                Container {
                    Stack {
                        for (element in model.state.card.upSide.elements) {
                            aligned(Alignment.Center) {
                                EditorElement(
                                    model,
                                    element
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun EditorToolbar() {
        Row(modifier = ExpandedWidth, arrangement = Arrangement.End) {
            for (editorFunction in EditorConfiguration.editorFunctions) {
                EditorToolbarButton(editorFunction, onClick = {
                    model.applyEditorFunction(editorFunction)
                })
            }
        }
    }

    @Composable
    private fun EditorToolbarButton(editorFunction: EditorFunctionConfig, onClick: () -> Unit) {
        val vector = +vectorResource(editorFunction.icon)
        Surface {
            Clickable(onClick = onClick) {
                Container(width = 32.dp, height = 32.dp) {
                    DrawVector(vectorImage = vector)
                }
            }
        }
    }

    @Composable
    private fun CardEditorLoading() {
        Column(modifier = Spacing(4.dp)) {
            Text(text = "Loading...")
        }
    }

    @Composable
    private fun CardEditorLoadingError() {
        Column(modifier = Spacing(4.dp)) {
            Text(text = "Error...")
        }
    }

    @Composable
    private fun LayersDropDown() {
        DropDownMenu(
            selectedItem = model.state.selectedElement,
            selectedItemLabelText = { it.type },
            items = ModelList<MemoryCardElement>().apply { addAll(model.state.card.upSide.elements) }
        ) { item ->
            LayersDropDownItem(item)
        }
    }

    @Composable
    private fun LayersDropDownItem(item: MemoryCardElement) {
        Surface(color = if (model.state.selectedElement == item) Color.LightGray else Color.Transparent) {
            Padding(padding = 2.dp) {
                FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
                    expanded(1f) {
                        Ripple(bounded = true) {
                            Clickable(onClick = { model.state.selectedElement = item }) {
                                Container(expanded = true) {
                                    Text(modifier = Spacing(4.dp), text = item.type)
                                }
                            }
                        }
                    }
                    inflexible {
                        val vector = +vectorResource(R.drawable.ic_move_up)
                        val onClick = { model.moveElementUp(item) }
                        IconButton(iconVector = vector, onClick = onClick)
                    }
                    inflexible {
                        val vector = +vectorResource(R.drawable.ic_move_down)
                        val onClick = { model.moveElementDown(item) }
                        IconButton(iconVector = vector, onClick = onClick)
                    }
                    inflexible {
                        val vector = +vectorResource(R.drawable.ic_editor_tool_delete)
                        val onClick = { model.removeElement(item) }
                        IconButton(iconVector = vector, onClick = onClick)
                    }
                }
            }
        }
    }
}


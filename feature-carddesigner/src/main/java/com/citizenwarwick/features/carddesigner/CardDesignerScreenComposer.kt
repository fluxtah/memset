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
package com.citizenwarwick.features.carddesigner

import MemsetMainTemplate
import androidx.compose.Composable
import androidx.compose.frames.ModelList
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.FlexColumn
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Padding
import androidx.ui.layout.Row
import androidx.ui.layout.Spacing
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import com.citizenwarwick.features.carddesigner.config.EditorConfiguration
import com.citizenwarwick.features.carddesigner.config.EditorFunctionConfig
import com.citizenwarwick.features.carddesigner.model.CardDesignerModel
import com.citizenwarwick.ui.card.MemoryCard
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.ElementControls
import com.citizenwarwick.memset.core.Destination
import com.citizenwarwick.memset.core.goto
import com.citizenwarwick.memset.core.model.LoadingState
import com.citizenwarwick.memset.core.model.MemoryCardElement
import com.citizenwarwick.memset.router.Composer
import com.citizenwarwick.ui.widgets.DropDownMenu
import com.citizenwarwick.ui.widgets.IconButton

class CardDesignerScreenComposer(
    private val model: CardDesignerModel
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
        Clickable(onClick = { model.state.layersDropDownOpen = false }) {
            FlexColumn {
                inflexible {
                    TopBar()
                    Column(modifier = Spacing(8.dp)) {
                        EditorToolbar()
                        EditorSurface()
                        Divider(height = 8.dp, color = Color.Transparent)
                        LayersDropDown()
                    }
                }
                expanded(1f) {
                    VerticalScroller(modifier = Spacing(8.dp)) {
                        ElementControls(model)
                    }
                }
            }
        }
    }

    @Composable
    private fun TopBar() {
        TopAppBar(
            title = { Text("Card Designer") },
            actionData = listOf("Save")
        ) {
            when (it) {
                "Save" -> {
                    IconButton(
                        vectorResourceId = R.drawable.ic_save_light,
                        onClick = {
                            model.saveCard()
                            goto(Destination.HomeScreen)
                        })
                }
            }
        }
    }

    @Composable
    private fun EditorSurface() {
        val onSurfaceClicked = {
            model.state.editElement = null
            model.state.selectedElement = null
            model.state.layersDropDownOpen = false
        }

        MemoryCard(
            card = model.state.card,
            onSurfaceClicked = onSurfaceClicked,
            onElementClick = { element -> model.state.selectedElement = element },
            onElementDoubleTap = { element -> model.state.editElement = element },
            isSelected = { model.state.selectedElement == it },
            isEditing = { model.state.editElement == it })
    }

    @Composable
    private fun EditorToolbar() {
        Row(modifier = ExpandedWidth, arrangement = Arrangement.End) {
            for (functionConfiguration in EditorConfiguration.editorFunctionConfiguration) {
                EditorToolbarButton(functionConfiguration, onClick = {
                    functionConfiguration.function.invoke(model.state)()
                })
            }
        }
    }

    @Composable
    private fun EditorToolbarButton(editorFunction: EditorFunctionConfig, onClick: () -> Unit) {
        val vector = +vectorResource(editorFunction.icon)
        IconButton(
            vectorResourceId = editorFunction.icon,
            onClick = onClick
        )
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
            selectedItemLabelText = { it.name },
            items = ModelList<MemoryCardElement>().apply { addAll(model.state.card.upSide.elements) },
            isOpen = model.state.layersDropDownOpen,
            onDropDownPressed = { model.state.layersDropDownOpen = it }
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
                                    Text(modifier = Spacing(4.dp), text = item.name)
                                }
                            }
                        }
                    }
                    inflexible {
                        val vector = +vectorResource(R.drawable.ic_move_up)
                        val onClick = { model.state.card.upSide.moveElementUp(item) }
                        IconButton(
                            iconVector = vector,
                            onClick = onClick
                        )
                    }
                    inflexible {
                        val vector = +vectorResource(R.drawable.ic_move_down)
                        val onClick = { model.state.card.upSide.moveElementDown(item) }
                        IconButton(
                            iconVector = vector,
                            onClick = onClick
                        )
                    }
                    inflexible {
                        val vector = +vectorResource(R.drawable.ic_editor_tool_delete)
                        val onClick: () -> Unit = { model.state.card.upSide.elements.remove(item) }
                        IconButton(
                            iconVector = vector,
                            onClick = onClick
                        )
                    }
                }
            }
        }
    }
}


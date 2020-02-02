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
import androidx.compose.ambient
import androidx.compose.frames.ModelList
import androidx.compose.remember
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.Arrangement
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutGravity
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Padding
import androidx.ui.layout.Row
import androidx.ui.layout.Spacing
import androidx.ui.material.Divider
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.unit.dp
import com.citizenwarwick.features.carddesigner.config.EditorConfiguration
import com.citizenwarwick.features.carddesigner.config.EditorFunctionConfig
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.ElementControls
import com.citizenwarwick.features.carddesigner.ui.elementcontrols.SurfacePropertyControls
import com.citizenwarwick.memset.core.MemoryCardRepository
import com.citizenwarwick.memset.core.di.get
import com.citizenwarwick.memset.core.model.LoadingState
import com.citizenwarwick.memset.core.model.MemoryCardElement
import com.citizenwarwick.memset.core.nav.MemsetDestination
import com.citizenwarwick.memset.core.observe
import com.citizenwarwick.memset.router.RouterAmbient
import com.citizenwarwick.memset.router.goto
import com.citizenwarwick.ui.card.MemoryCard
import com.citizenwarwick.ui.widgets.DropDownMenu
import com.citizenwarwick.ui.widgets.IconButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

@Composable
fun CardDesignerScreen(repository: MemoryCardRepository = get(), cardUuid: String? = null) {
    val state = remember { CardDesignerState() }

    if (cardUuid != null && cardUuid.isNotEmpty()) {
        observe(repository.getCard(cardUuid)) {
            onStart = {
                state.loadingState = LoadingState.Loading
            }
            onResult = {
                state.loadingState = LoadingState.Loaded
                state.card = it
            }
        }
    }

    val router = ambient(RouterAmbient)
    val onCardSaved: () -> Unit = {
        CoroutineScope(Main).launch {
            // TODO temporary for dev04
            if (cardUuid == state.card.uuid) {
                repository.updateCard(state.card)
            } else {
                repository.saveCard(state.card)
            }
            router.goto(MemsetDestination.HomeScreen)
        }
    }

    MemsetMainTemplate {
        when (state.loadingState) {
            LoadingState.Loaded -> CardEditorContent(state, onCardSaved)
            LoadingState.Loading -> CardEditorLoading()
            LoadingState.Error -> CardEditorLoadingError()
        }
    }
}

@Composable
private fun CardEditorContent(state: CardDesignerState, onCardSaved: () -> Unit) {
    Clickable(onClick = { state.layersDropDownOpen = false }) {
        Column {
            TopBar(onCardSaved)
            Column(modifier = LayoutPadding(8.dp)) {
                EditorToolbar(state)
                EditorSurface(state)
                Divider(height = 8.dp, color = Color.Transparent)
                LayersDropDown(state)
            }

            VerticalScroller(modifier = LayoutPadding(8.dp) + LayoutFlexible(1f)) {
                when (val element = state.selectedElement) {
                    null -> {
                        SurfacePropertyControls(state.card.upSide)
                    }
                    else -> {
                        ElementControls(element)
                    }
                }
            }
        }
    }
}

@Composable
private fun TopBar(onCardSaved: () -> Unit) {
    TopAppBar(
        title = { Text("Card Designer") },
        actionData = listOf("Save")
    ) {
        when (it) {
            "Save" -> {
                IconButton(
                    vectorResourceId = R.drawable.ic_save_light,
                    onClick = onCardSaved
                )
            }
        }
    }
}

@Composable
private fun EditorSurface(state: CardDesignerState) {
    val onSurfaceClicked = {
        state.editElement = null
        state.selectedElement = null
        state.layersDropDownOpen = false
    }

    MemoryCard(
        card = state.card,
        onSurfaceClicked = onSurfaceClicked,
        onElementClick = { element -> state.selectedElement = element },
        onElementDoubleTap = { element -> state.editElement = element },
        isSelected = { state.selectedElement == it },
        isEditing = { state.editElement == it })
}

@Composable
private fun EditorToolbar(state: CardDesignerState) {
    Row(modifier = LayoutWidth.Fill, arrangement = Arrangement.End) {
        for (functionConfiguration in EditorConfiguration.editorFunctionConfiguration) {
            EditorToolbarButton(functionConfiguration, onClick = {
                functionConfiguration.function.invoke(state)()
            })
        }
    }
}

@Composable
private fun EditorToolbarButton(editorFunction: EditorFunctionConfig, onClick: () -> Unit) {
    val vector = vectorResource(editorFunction.icon)
    IconButton(
        vectorResourceId = editorFunction.icon,
        onClick = onClick
    )
}

@Composable
private fun CardEditorLoading() {
    Column(modifier = LayoutPadding(4.dp)) {
        Text(text = "Loading...")
    }
}

@Composable
private fun CardEditorLoadingError() {
    Column(modifier = LayoutPadding(4.dp)) {
        Text(text = "Error...")
    }
}

@Composable
private fun LayersDropDown(state: CardDesignerState) {
    DropDownMenu(
        selectedItem = state.selectedElement,
        selectedItemLabelText = { it.name },
        items = ModelList<MemoryCardElement>().apply { addAll(state.card.upSide.elements) },
        isOpen = state.layersDropDownOpen,
        onDropDownPressed = { state.layersDropDownOpen = it }
    ) { item ->
        LayersDropDownItem(state, item)
    }
}

@Composable
private fun LayersDropDownItem(state: CardDesignerState, item: MemoryCardElement) {
    Surface(color = if (state.selectedElement == item) Color.LightGray else Color.Transparent) {
        Padding(padding = 2.dp) {
            Ripple(bounded = true) {
                Row {
                    Container(modifier = LayoutGravity.Center + LayoutFlexible(1f)) {
                        Clickable(onClick = { state.selectedElement = item }) {
                            Container(expanded = true) {
                                Text(modifier = Spacing(4.dp), text = item.name)
                            }
                        }
                    }
                    IconButton(
                        modifier = LayoutGravity.Center,
                        iconVector = vectorResource(R.drawable.ic_move_up),
                        onClick = { state.card.upSide.moveElementUp(item) }
                    )
                    IconButton(
                        modifier = LayoutGravity.Center,
                        iconVector = vectorResource(R.drawable.ic_move_down),
                        onClick = { state.card.upSide.moveElementDown(item) }
                    )
                    IconButton(
                        modifier = LayoutGravity.Center,
                        iconVector = vectorResource(R.drawable.ic_editor_tool_delete),
                        onClick = { state.card.upSide.elements.remove(item) }
                    )
                }
            }
        }
    }
}


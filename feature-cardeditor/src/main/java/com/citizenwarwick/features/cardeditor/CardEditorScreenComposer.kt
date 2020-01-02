package com.citizenwarwick.features.cardeditor

import MemsetMainTemplate
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Arrangement
import androidx.ui.layout.AspectRatio
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.Row
import androidx.ui.layout.Spacing
import androidx.ui.layout.Stack
import androidx.ui.material.surface.Card
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import com.citizenwarwick.features.cardeditor.config.EditorFunctionConfig
import com.citizenwarwick.features.cardeditor.ui.elementcontrols.ElementControls
import com.citizenwarwick.features.cardeditor.ui.elements.EditorElement
import com.citizenwarwick.memset.router.Composer

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
                model.state.selectedElement?.let {
                    ElementControls(it)
                }
            }
        }
    }

    @Composable
    private fun EditorSurface() {
        Card(shape = RoundedCornerShape(4.dp), elevation = 4.dp, modifier = AspectRatio(1.7f) wraps ExpandedWidth) {
            Container {
                Stack {
                    for (element in model.state.card.frontElements) {
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

    @Composable
    private fun EditorToolbar() {
        Row(modifier = ExpandedWidth, arrangement = Arrangement.End) {
            for (editorFunction in model.state.editorConfiguration.editorFunctions) {
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
}


package com.citizenwarwick.features.cardeditor

import MemsetMainTemplate
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.TextUnit
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Align
import androidx.ui.layout.Arrangement
import androidx.ui.layout.AspectRatio
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Row
import androidx.ui.layout.Spacing
import androidx.ui.layout.Stack
import androidx.ui.material.Button
import androidx.ui.material.Slider
import androidx.ui.material.SliderPosition
import androidx.ui.material.surface.Card
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.toStringAsFixed
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.Companion.ELEMENT_PROPERTY_TEXT_SIZE
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.Companion.ELEMENT_TYPE_BG_IMAGE
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.Companion.ELEMENT_TYPE_TEXT
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.Companion.ELEMENT_TYPE_TEXT_DEFAULT_MAX_SIZE_EM
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.Companion.ELEMENT_TYPE_TEXT_DEFAULT_MIN_SIZE_EM
import com.citizenwarwick.features.cardeditor.config.EditorFunctionConfig
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement
import com.citizenwarwick.memset.router.ActiveRouter
import com.citizenwarwick.memset.router.Composer

class CardEditorScreenComposer(
    private val model: CardEditorModel
) : Composer() {
    override fun compose() {
        MemsetMainTemplate {
            when (model.state.loadingState) {
                LoadingState.Loading -> CardEditorLoading()
                LoadingState.Loaded -> CardEditorContent()
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
                HomeButton()
            }
        }
    }

    @Composable
    private fun HomeButton() {
        val router = +ambient(ActiveRouter)
        Button(text = "Go Home", onClick = {
            router.goto("http://memset.com/")
        })
    }

    @Composable
    private fun EditorSurface() {
        Card(shape = RoundedCornerShape(4.dp), elevation = 4.dp, modifier = AspectRatio(1.7f) wraps ExpandedWidth) {
            Container {
                Stack {
                    for (element in model.state.card.frontElements) {
                        aligned(Alignment.Center) {
                            EditorElement(element)
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun EditorElement(element: MemoryCardElement) {
        when (element.type) {
            ELEMENT_TYPE_TEXT -> TextElement(element)
            ELEMENT_TYPE_BG_IMAGE -> BackgroundImageElement(element)
        }
    }

    @Composable
    private fun TextElement(element: MemoryCardElement) {
        val fontSize = element.properties[ELEMENT_PROPERTY_TEXT_SIZE]?.toFloat()
            ?: EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_SIZE_EM

        Align(alignment = Alignment.valueOf(element.properties["alignment"]!!)) {
            Clickable(onClick = { model.selectElement(element) }) {
                if (model.state.selectedElement == element) {
                    Surface(color = Color.Yellow) {
                        Text(text = element.type, style = TextStyle(fontSize = TextUnit.Em(fontSize)))
                    }
                } else {
                    Text(text = element.type, style = TextStyle(fontSize = TextUnit.Em(fontSize)))
                }
            }
        }
    }

    @Composable
    private fun BackgroundImageElement(element: MemoryCardElement) {
        Align(alignment = Alignment.TopCenter) {
            Clickable(onClick = { model.selectElement(element) }) {
                if (model.state.selectedElement == element) {
                    Surface(color = Color.Yellow) {
                        Text(text = element.type)
                    }
                } else {
                    Text(text = element.type)
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

    @Composable
    private fun ElementControls(element: MemoryCardElement) {
        when (element.type) {
            ELEMENT_TYPE_TEXT -> {
                TextElementControls(element)
            }
        }
    }

    @Composable
    fun TextElementControls(element: MemoryCardElement) {
        Column {
            SliderElementControl(
                element = element,
                propertyName = ELEMENT_PROPERTY_TEXT_SIZE,
                label = "Text Size",
                defaultValue = EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_SIZE_EM,
                valueRange = ELEMENT_TYPE_TEXT_DEFAULT_MIN_SIZE_EM..ELEMENT_TYPE_TEXT_DEFAULT_MAX_SIZE_EM
            )
        }
    }

    @Composable
    private fun SliderElementControl(
        element: MemoryCardElement,
        propertyName: String,
        label: String,
        defaultValue: Float,
        valueRange: ClosedFloatingPointRange<Float>
    ) {
        val fontSize = element.properties[propertyName]?.toFloat() ?: defaultValue

        FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
            expanded(1f) {
                Text(text = label)
            }
            expanded(2f) {
                Slider(
                    position = SliderPosition(fontSize, valueRange),
                    modifier = ExpandedWidth,
                    onValueChange = {
                        element.properties[propertyName] = it.toString()
                    })
            }
            inflexible {
                Text(text = "${fontSize.toStringAsFixed(1)}em")
            }
        }
    }
}

package com.citizenwarwick.features.cardeditor.ui.elements

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.PxPosition
import androidx.ui.core.Text
import androidx.ui.core.TextField
import androidx.ui.core.TextUnit
import androidx.ui.core.dp
import androidx.ui.core.gesture.DoubleTapGestureDetector
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Align
import androidx.ui.layout.Spacing
import androidx.ui.text.TextStyle
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.ELEMENT_PROPERTY_TEXT_ALIGNMENT
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.ELEMENT_PROPERTY_TEXT_COLOR
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.ELEMENT_PROPERTY_TEXT_CONTENT
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.ELEMENT_PROPERTY_TEXT_SIZE
import com.citizenwarwick.features.cardeditor.model.CardEditorModel
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement
import com.citizenwarwick.features.cardeditor.ui.SelectionBorder

@Composable
fun TextElement(model: CardEditorModel, element: MemoryCardElement) {
    Align(element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT]
        ?.let { Alignment.valueOf(it) } ?: Alignment.Center
    ) {
        val onTextClick = { model.state.selectedElement = element }
        val onTextDoubleTap = { _: PxPosition -> model.state.editElement = element }

        Clickable(onClick = onTextClick) {
            when {
                model.state.editElement == element -> {
                    SelectionBorder { Text(element, true) }
                }
                model.state.selectedElement == element -> {
                    SelectionBorder {
                        DoubleTapGestureDetector(onDoubleTap = onTextDoubleTap) {
                            Text(element)
                        }
                    }
                }
                else -> {
                    DoubleTapGestureDetector(onDoubleTap = onTextDoubleTap) { Text(element) }
                }
            }
        }
    }
}

@Composable
private fun Text(
    element: MemoryCardElement,
    inEdit: Boolean = false
) {
    val fontSize = element.properties[ELEMENT_PROPERTY_TEXT_SIZE]?.toFloat()
        ?: EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_SIZE_EM
    val text = element.properties[ELEMENT_PROPERTY_TEXT_CONTENT]
    val textStyle = TextStyle(
        background = Color.Transparent,
        fontSize = TextUnit.Em(fontSize),
        color = element.properties[ELEMENT_PROPERTY_TEXT_COLOR]?.toInt()?.let { Color(it) }
    )
    val spacingTop = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_TOP]?.toFloat()
        ?: 0f
    val spacingRight = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_RIGHT]?.toFloat()
        ?: 0f
    val spacingBottom = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_BOTTOM]?.toFloat()
        ?: 0f
    val spacingLeft = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SPACING_LEFT]?.toFloat()
        ?: 0f

    val spacing = Spacing(spacingLeft.dp, spacingTop.dp, spacingRight.dp, spacingBottom.dp)

    if (inEdit) {
        TextField(modifier = spacing, value = text ?: "", textStyle = textStyle, onValueChange = {
            element.properties[ELEMENT_PROPERTY_TEXT_CONTENT] = it
        })
    } else {
        Text(modifier = spacing, text = text ?: "text", style = textStyle)
    }
}


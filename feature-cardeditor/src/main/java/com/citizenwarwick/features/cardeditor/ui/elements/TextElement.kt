package com.citizenwarwick.features.cardeditor.ui.elements

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.TextUnit
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Align
import androidx.ui.text.TextStyle
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.ELEMENT_PROPERTY_TEXT_ALIGNMENT
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.ELEMENT_PROPERTY_TEXT_COLOR
import com.citizenwarwick.features.cardeditor.model.CardEditorModel
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun TextElement(model: CardEditorModel, element: MemoryCardElement) {
    val fontSize = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SIZE]?.toFloat()
        ?: EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_SIZE_EM

    Align(
        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT]?.let { Alignment.valueOf(it) } ?: Alignment.Center
    ) {
        Clickable(onClick = { model.selectElement(element) }) {
            Text(
                text = element.type,
                style = TextStyle(
                    background = if (model.state.selectedElement == element) Color.LightGray else null,
                    fontSize = TextUnit.Em(fontSize),
                    color = element.properties[ELEMENT_PROPERTY_TEXT_COLOR]?.toInt()?.let { Color(it) }
                )
            )
        }
    }
}

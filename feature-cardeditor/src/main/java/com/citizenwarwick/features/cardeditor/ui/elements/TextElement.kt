package com.citizenwarwick.features.cardeditor.ui.elements

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.TextUnit
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Align
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import com.citizenwarwick.features.cardeditor.CardEditorModel
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun TextElement(model: CardEditorModel, element: MemoryCardElement) {
    val fontSize = element.properties[EditorConfiguration.ELEMENT_PROPERTY_TEXT_SIZE]?.toFloat()
        ?: EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_SIZE_EM

    Align(
        alignment = Alignment.valueOf(
            element.properties["alignment"]!!
        )
    ) {
        Clickable(onClick = { model.selectElement(element) }) {
            if (model.state.selectedElement == element) {
                Surface(color = Color.Yellow) {
                    Text(
                        text = element.type,
                        style = TextStyle(
                            fontSize = TextUnit.Em(fontSize)
                        )
                    )
                }
            } else {
                Text(
                    text = element.type,
                    style = TextStyle(
                        fontSize = TextUnit.Em(fontSize)
                    )
                )
            }
        }
    }
}

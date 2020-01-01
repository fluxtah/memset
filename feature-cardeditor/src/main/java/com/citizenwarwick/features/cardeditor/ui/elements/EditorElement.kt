package com.citizenwarwick.features.cardeditor.ui.elements

import androidx.compose.Composable
import com.citizenwarwick.features.cardeditor.CardEditorModel
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun EditorElement(model: CardEditorModel, element: MemoryCardElement) {
    when (element.type) {
        EditorConfiguration.ELEMENT_TYPE_TEXT -> TextElement(
            model,
            element
        )
        EditorConfiguration.ELEMENT_TYPE_BG_IMAGE -> BackgroundImageElement(
            model,
            element
        )
    }
}

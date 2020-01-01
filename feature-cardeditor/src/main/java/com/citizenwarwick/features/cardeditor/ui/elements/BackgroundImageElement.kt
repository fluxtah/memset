package com.citizenwarwick.features.cardeditor.ui.elements

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Align
import androidx.ui.material.surface.Surface
import com.citizenwarwick.features.cardeditor.CardEditorModel
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun BackgroundImageElement(model: CardEditorModel, element: MemoryCardElement) {
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

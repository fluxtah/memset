package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun ElementControls(element: MemoryCardElement) {
    when (element.type) {
        EditorConfiguration.ELEMENT_TYPE_TEXT -> {
            TextElementControls(element)
        }
    }
}

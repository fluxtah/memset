package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.CardEditorModel

@Composable
fun ElementControls(model: CardEditorModel) {
    val selectedElement = model.state.selectedElement
    when (selectedElement?.type) {
        EditorConfiguration.ELEMENT_TYPE_TEXT -> {
            TextElementPropertyControls(selectedElement)
        }
        EditorConfiguration.ELEMENT_TYPE_SHAPE_OVAL -> {
            OvalElementPropertyControls(selectedElement)
        }
        null -> {
            SurfacePropertyControls(model)
        }
    }
}


package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
import androidx.ui.layout.Column
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.CardEditorModel

@Composable
fun SurfacePropertyControls(model: CardEditorModel) {
    Column {
        ColorPropertyControl("Color", model.state.card.upSide.properties,
            EditorConfiguration.SURFACE_PROPERTY_COLOR
        )
    }
}

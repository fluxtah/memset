package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
import androidx.ui.layout.Column
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun TextElementControls(element: MemoryCardElement) {
    Column {
        SliderElementControl(
            element = element,
            propertyName = EditorConfiguration.ELEMENT_PROPERTY_TEXT_SIZE,
            label = "Text Size",
            defaultValue = EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_SIZE_EM,
            valueRange = EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_MIN_SIZE_EM..EditorConfiguration.ELEMENT_TYPE_TEXT_DEFAULT_MAX_SIZE_EM
        )
        TextAlignmentEditorControl(element)
        TextColorElementControl(element)
    }
}

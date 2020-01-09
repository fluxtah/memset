package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun OvalElementPropertyControls(element: MemoryCardElement) {
    val ovalWidth = element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_WIDTH]?.toFloat() ?: 64f
    val ovalHeight = element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_HEIGHT]?.toFloat() ?: 64f
    Column {
        ColorPropertyControl(
            "Color",
            element.properties,
            EditorConfiguration.ELEMENT_PROPERTY_OVAL_COLOR,
            defaultColor = Color.Red
        )

        SliderPropertyControl(
            label = "Width",
            initialValue = ovalWidth,
            valueRange = 8f..512f,
            onValueChanged = {
                element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_WIDTH] =
                    it.toString()
            }
        )

        SliderPropertyControl(
            label = "Height",
            initialValue = ovalHeight,
            valueRange = 8f..512f,
            onValueChanged = {
                element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_HEIGHT] =
                    it.toString()
            }
        )
    }
}

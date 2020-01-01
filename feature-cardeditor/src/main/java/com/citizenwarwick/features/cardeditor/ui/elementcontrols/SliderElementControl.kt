package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.FlexRow
import androidx.ui.material.Slider
import androidx.ui.material.SliderPosition
import androidx.ui.toStringAsFixed
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun SliderElementControl(
    element: MemoryCardElement,
    propertyName: String,
    label: String,
    defaultValue: Float,
    valueRange: ClosedFloatingPointRange<Float>
) {
    val fontSize = element.properties[propertyName]?.toFloat() ?: defaultValue

    FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
        expanded(1f) {
            Text(text = label)
        }
        expanded(2f) {
            Slider(
                position = SliderPosition(fontSize, valueRange),
                modifier = ExpandedWidth,
                onValueChange = {
                    element.properties[propertyName] = it.toString()
                })
        }
        inflexible {
            Text(text = "${fontSize.toStringAsFixed(1)}em")
        }
    }
}

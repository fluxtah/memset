package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.FlexRow
import androidx.ui.material.Slider
import androidx.ui.material.SliderPosition
import androidx.ui.toStringAsFixed

@Composable
fun SliderPropertyControl(
    label: String,
    initialValue: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChanged: (value: Float) -> Unit
) {

    FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
        expanded(1f) {
            Text(text = label)
        }
        expanded(2f) {
            Slider(
                position = SliderPosition(initialValue, valueRange),
                modifier = ExpandedWidth,
                onValueChange = {
                    onValueChanged(it)
                })
        }
        inflexible {
            Text(text = initialValue.toStringAsFixed(1))
        }
    }
}

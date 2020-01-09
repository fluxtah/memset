package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
import androidx.compose.frames.ModelMap
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.Color.Companion.Black
import androidx.ui.graphics.Color.Companion.Blue
import androidx.ui.graphics.Color.Companion.Cyan
import androidx.ui.graphics.Color.Companion.Gray
import androidx.ui.graphics.Color.Companion.Green
import androidx.ui.graphics.Color.Companion.Red
import androidx.ui.graphics.Color.Companion.White
import androidx.ui.graphics.Color.Companion.Yellow
import androidx.ui.graphics.lerp
import androidx.ui.graphics.toArgb
import androidx.ui.layout.Container
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Spacing
import androidx.ui.material.surface.Surface

@Composable
fun ColorPropertyControl(
    label: String,
    properties: ModelMap<String, String>,
    propertyKey: String,
    defaultColor: Color = White
) {
    val selectedColor = properties[propertyKey]?.toInt()?.let { Color(it) } ?: defaultColor

    FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
        expanded(1f) {
            Text(text = label)
        }
        listOf(Black, Red, Yellow, Blue, Green, Cyan, Gray, White).forEach {
            inflexible {
                ColorOptionButton(properties, propertyKey, it, selectedColor == it)
            }
        }
    }
}

@Composable
fun ColorOptionButton(properties: ModelMap<String, String>, propertyKey: String, color: Color, selected: Boolean) {
    Surface(
        modifier = Spacing(2.dp),
        shape = RoundedCornerShape(4.dp),
        color = color.let { if (selected) lerp(it, White, 0.1f) else it },
        elevation = if (selected) 6.dp else 0.dp,
        border = Border(Color.LightGray, width = if (selected) 0.dp else 2.dp)
    ) {
        Clickable(onClick = {
            properties[propertyKey] = color.toArgb().toString()
        }) {
            Container(width = 32.dp, height = 32.dp) {}
        }
    }
}



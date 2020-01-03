package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
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
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.ELEMENT_PROPERTY_TEXT_COLOR
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun TextColorElementControl(
    element: MemoryCardElement
) {
    val selectedColor = element.properties[ELEMENT_PROPERTY_TEXT_COLOR]?.toInt()?.let { Color(it) } ?: Black

    FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
        expanded(1f) {
            Text(text = "Text Color")
        }
        listOf(Black, Red, Yellow, Blue, Green, Cyan, Gray, White).forEach {
            inflexible {
                ColorOptionButton(element, it, selectedColor == it)
            }
        }
    }
}

@Composable
fun ColorOptionButton(element: MemoryCardElement, color: Color, selected: Boolean) {
    Surface(
        modifier = Spacing(2.dp),
        shape = RoundedCornerShape(4.dp),
        color = color.let { if (selected) lerp(it, White, 0.1f) else it },
        elevation = if (selected) 6.dp else 0.dp,
        border = Border(Color.LightGray, width = 2.dp)
    ) {
        Clickable(onClick = {
            element.properties[ELEMENT_PROPERTY_TEXT_COLOR] = color.toArgb().toString()
        }) {
            Container(width = 32.dp, height = 32.dp) {}
        }
    }
}



package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun TextColorElementControl(
    element: MemoryCardElement
) {
    FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
        expanded(1f) {
            Text(text = "Text Color")
        }
        val colors = (+MaterialTheme.colors())
        inflexible {
            ColorOptionButton(Color.Black)
        }
        inflexible {
            ColorOptionButton(Color.Red)
        }
        inflexible {
            ColorOptionButton(Color.Yellow)
        }
        inflexible {
            ColorOptionButton(Color.Blue)
        }
        inflexible {
            ColorOptionButton(Color.Green)
        }
        inflexible {
            ColorOptionButton(Color.Cyan)
        }
        inflexible {
            ColorOptionButton(Color.Gray)
        }
        inflexible {
            ColorOptionButton(Color.White)
        }
    }
}

@Composable
fun ColorOptionButton(color: Color) {
    Surface(
        modifier = Spacing(2.dp),
        shape = RoundedCornerShape(4.dp),
        color = color,
        border = Border(Color.Companion.Black, width = 1.dp)
    ) {
        Container(width = 32.dp, height = 32.dp) {}
    }
}



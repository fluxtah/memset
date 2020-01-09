package com.citizenwarwick.features.cardeditor.ui.elements

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Density
import androidx.ui.core.Dp
import androidx.ui.core.dp
import androidx.ui.core.withDensity
import androidx.ui.engine.geometry.Rect
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.GenericShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Align
import androidx.ui.layout.Container
import androidx.ui.layout.Size
import androidx.ui.material.surface.Surface
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration
import com.citizenwarwick.features.cardeditor.model.CardEditorModel
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement
import com.citizenwarwick.features.cardeditor.ui.SelectionBorder

@Composable
fun OvalShapeElement(model: CardEditorModel, element: MemoryCardElement) {
    val ovalWidth = element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_WIDTH]?.toFloat()?.dp ?: 64.dp
    val ovalHeight = element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_HEIGHT]?.toFloat()?.dp ?: 64.dp
    val color = element.properties[EditorConfiguration.ELEMENT_PROPERTY_OVAL_COLOR]?.let {
        Color(it.toInt())
    } ?: Color.Red

    Align(alignment = Alignment.Center) {
        Clickable(onClick = { model.state.selectedElement = element }) {
            if (model.state.selectedElement == element) {
                SelectionBorder {
                    Oval(ovalWidth, ovalHeight, color)
                }
            } else {
                Oval(ovalWidth, ovalHeight, color)
            }
        }
    }
}

@Composable
private fun Oval(
    ovalWidth: Dp,
    ovalHeight: Dp,
    color: Color
) {
    val density = Density(+ambient(ContextAmbient))
    val widthPx = +withDensity(density) { ovalWidth.toPx().value }
    val heightPx = +withDensity(density) { ovalHeight.toPx().value }
    Container(width = ovalWidth, height = ovalHeight, expanded = true) {
        Surface(color = Color.Transparent, modifier = Size(ovalWidth, ovalHeight)) {
            DrawShape(shape = newOvalShape(widthPx, heightPx), color = color)
        }
    }
}

fun newOvalShape(widthPx: Float, heightPx: Float) = GenericShape {
    addOval(Rect(0f, 0f, widthPx, heightPx))
}

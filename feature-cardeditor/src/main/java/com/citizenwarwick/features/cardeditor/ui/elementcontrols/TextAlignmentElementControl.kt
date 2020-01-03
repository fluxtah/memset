package com.citizenwarwick.features.cardeditor.ui.elementcontrols

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.Container
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.layout.FlexRow
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import com.citizenwarwick.features.cardeditor.R
import com.citizenwarwick.features.cardeditor.config.EditorConfiguration.ELEMENT_PROPERTY_TEXT_ALIGNMENT
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement

@Composable
fun TextAlignmentEditorControl(
    element: MemoryCardElement
) {
    val textAlignment = element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT]?.let {
        Alignment.valueOf(it)
    } ?: Alignment.Center

    FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
        expanded(1f) {
            Text(text = "Text Alignment")
        }
        inflexible {
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_align_left,
                (textAlignment == Alignment.TopLeft ||
                    textAlignment == Alignment.CenterLeft ||
                    textAlignment == Alignment.BottomLeft)
            ) {
                when (textAlignment) {
                    Alignment.Center, Alignment.CenterRight ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.CenterLeft.name
                    Alignment.BottomCenter, Alignment.BottomRight ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.BottomLeft.name
                    Alignment.TopCenter, Alignment.TopRight ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.TopLeft.name
                    else ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.CenterLeft.name
                }
            }
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_align_center,
                (textAlignment == Alignment.TopCenter ||
                    textAlignment == Alignment.BottomCenter ||
                    textAlignment == Alignment.Center)
            ) {
                when (textAlignment) {
                    Alignment.CenterRight, Alignment.CenterLeft ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.Center.name
                    Alignment.BottomRight, Alignment.BottomLeft ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.BottomCenter.name
                    Alignment.TopRight, Alignment.TopLeft ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.TopCenter.name
                    else ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.Center.name
                }
            }
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_align_right,
                (textAlignment == Alignment.TopRight ||
                    textAlignment == Alignment.BottomRight ||
                    textAlignment == Alignment.CenterRight)
            ) {
                when (textAlignment) {
                    Alignment.Center, Alignment.CenterLeft ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.CenterRight.name
                    Alignment.BottomCenter, Alignment.BottomLeft ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.BottomRight.name
                    Alignment.TopCenter, Alignment.TopLeft ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.TopRight.name
                    else ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.CenterRight.name
                }
            }
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_vertical_align_bottom,
                (textAlignment == Alignment.BottomRight ||
                    textAlignment == Alignment.BottomCenter ||
                    textAlignment == Alignment.BottomLeft)
            ) {
                when (textAlignment) {
                    Alignment.Center, Alignment.TopCenter ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.BottomCenter.name
                    Alignment.CenterLeft, Alignment.TopLeft ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.BottomLeft.name
                    Alignment.CenterRight, Alignment.TopRight ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.BottomRight.name
                    else ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.BottomCenter.name
                }
            }
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_vertical_align_center,
                (textAlignment == Alignment.Center ||
                    textAlignment == Alignment.CenterLeft ||
                    textAlignment == Alignment.CenterRight)
            ) {
                when (textAlignment) {
                    Alignment.BottomCenter, Alignment.TopCenter ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.Center.name
                    Alignment.TopLeft, Alignment.BottomLeft ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.CenterLeft.name
                    Alignment.TopRight, Alignment.BottomRight ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.CenterRight.name
                    else ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.Center.name
                }
            }
            EditorControlOptionButton(
                R.drawable.ic_editor_tool_vertical_align_top,
                (textAlignment == Alignment.TopRight ||
                    textAlignment == Alignment.TopCenter ||
                    textAlignment == Alignment.TopLeft)
            ) {
                when (textAlignment) {
                    Alignment.Center, Alignment.BottomCenter ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.TopCenter.name
                    Alignment.CenterRight, Alignment.BottomRight ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.TopRight.name
                    Alignment.CenterLeft, Alignment.BottomLeft ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.TopLeft.name
                    else ->
                        element.properties[ELEMENT_PROPERTY_TEXT_ALIGNMENT] = Alignment.TopCenter.name
                }
            }
        }
    }
}

@Composable
private fun EditorControlOptionButton(@DrawableRes vectorResourceId: Int, selected: Boolean, onClick: () -> Unit) {
    val vector = +vectorResource(vectorResourceId)
    val colors = (+MaterialTheme.colors())
    val selectedColor = if (selected) {
        colors.primary
    } else {
        Color.Transparent
    }
    val selectedOnColor = if (selected) {
        colors.onPrimary
    } else {
        Color.Transparent
    }
    Surface(color = selectedColor, shape = RoundedCornerShape(4.dp), modifier = Spacing(2.dp)) {
        Clickable(onClick = onClick) {
            Container(width = 32.dp, height = 32.dp) {
                DrawVector(vectorImage = vector, tintColor = selectedOnColor)
            }
        }
    }
}

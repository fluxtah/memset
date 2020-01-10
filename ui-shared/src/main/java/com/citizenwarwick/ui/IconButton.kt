package com.citizenwarwick.ui

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Container
import androidx.ui.layout.Spacing
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource

@Composable
fun IconButton(@DrawableRes vectorResourceId: Int, selected: Boolean = false, onClick: () -> Unit) {
    val vector = +vectorResource(vectorResourceId)
    IconButton(vector, selected, onClick)
}

@Composable
fun IconButton(iconVector: VectorAsset, selected: Boolean = false, onClick: () -> Unit) {
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
    Surface(
        color = selectedColor,
        shape = RoundedCornerShape(4.dp),
        modifier = Spacing(2.dp)
    ) {
        Ripple(bounded = true) {
            Clickable(onClick = onClick) {
                Container(width = 32.dp, height = 32.dp) {
                    DrawVector(
                        vectorImage = iconVector,
                        tintColor = selectedOnColor
                    )
                }
            }
        }
    }
}

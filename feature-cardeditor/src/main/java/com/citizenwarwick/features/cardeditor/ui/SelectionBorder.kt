package com.citizenwarwick.features.cardeditor.ui

import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.material.surface.Surface

@Composable
fun SelectionBorder(child: @Composable() () -> Unit) {
    Surface(
        shape = RoundedCornerShape(2.dp),
        color = Color.Transparent,
        border = Border(
            color = Color.Black,
            width = 2.dp
        )
    ) {
        child()
    }
}

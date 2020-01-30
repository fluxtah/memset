package com.citizenwarwick.ui

import androidx.compose.Composable
import androidx.ui.core.Layout
import androidx.ui.unit.IntPx
import androidx.ui.unit.ipx

@Composable
fun MeasureObserver(onMeasure: (IntPx, IntPx) -> Unit, children: @Composable() () -> Unit) {
    Layout(children) { measurables, constraints ->
        if (measurables.size > 1) {
            throw IllegalStateException("MeasureObserver can have only one direct measurable child!")
        }
        val measurable = measurables.firstOrNull()
        if (measurable == null) {
            onMeasure(constraints.minWidth, constraints.minHeight)
            layout(constraints.minWidth, constraints.minHeight) {}
        } else {
            val placeable = measurable.measure(constraints)
            onMeasure(placeable.width, placeable.height)
            layout(placeable.width, placeable.height) {
                placeable.place(0.ipx, 0.ipx)
            }
        }
    }
}

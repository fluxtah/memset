package com.citizenwarwick.ui.card

import android.util.Log
import androidx.compose.Composable
import androidx.ui.core.DensityAmbient
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutAspectRatio
import androidx.ui.layout.LayoutWidth
import androidx.ui.layout.Stack
import androidx.ui.material.surface.Card
import androidx.ui.unit.dp
import com.citizenwarwick.memset.core.model.MemoryCard
import com.citizenwarwick.memset.core.model.MemoryCardElement
import com.citizenwarwick.ui.MeasureObserver
import com.citizenwarwick.ui.card.elements.MemoryCardElement

@Composable
fun MemoryCard(
    card: MemoryCard,
    onSurfaceClicked: () -> Unit = {},
    onElementClick: (MemoryCardElement) -> Unit = {},
    onElementDoubleTap: (MemoryCardElement) -> Unit = {},
    isSelected: (MemoryCardElement) -> Boolean = { false },
    isEditing: (MemoryCardElement) -> Boolean = { false }
) {
    val density = DensityAmbient.current
    Clickable(onClick = onSurfaceClicked) {
        MeasureObserver(onMeasure = { x, y ->
            val zeroSize = card.designerSurfaceWidth == 0f && card.designerSurfaceHeight == 0f
            if (zeroSize) {
                Log.d("memsetlog", "Got size x: $x, y:$y")
                with(density) {
                    card.designerSurfaceWidth = x.toDp().value
                    card.designerSurfaceHeight = y.toDp().value
                }
            }
        }) {
            Card(
                shape = RoundedCornerShape(4.dp),
                elevation = 4.dp,
                modifier = LayoutAspectRatio(1.78f) + LayoutWidth.Fill,
                color = card.upSide.color
            ) {
                Container {
                    Stack {
                        for (element in card.upSide.elements) {
                            MemoryCardElement(
                                element = element,
                                onClick = onElementClick,
                                onDoubleTap = onElementDoubleTap,
                                isSelected = isSelected(element),
                                isEditing = isEditing(element)
                            )
                        }
                    }
                }
            }
        }
    }
}

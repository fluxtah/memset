package com.citizenwarwick.ui.card

import android.util.Log
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Density
import androidx.ui.core.dp
import androidx.ui.core.withDensity
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.AspectRatio
import androidx.ui.layout.Container
import androidx.ui.layout.ExpandedWidth
import androidx.ui.layout.Stack
import androidx.ui.material.surface.Card
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
    val density = Density(+ambient(ContextAmbient))

    Clickable(onClick = onSurfaceClicked) {
        MeasureObserver(onMeasure = { x, y ->
            val zeroSize = card.designerSurfaceWidth == 0f && card.designerSurfaceHeight == 0f
            if (zeroSize) {
                Log.d("memsetlog", "Got size x: $x, y:$y")
                card.designerSurfaceWidth = +withDensity(density) { x.value.toDp().value }
                card.designerSurfaceHeight = +withDensity(density) { y.value.toDp().value }
            }

        }) {
            Card(
                shape = RoundedCornerShape(4.dp),
                elevation = 4.dp,
                modifier = AspectRatio(1.7f) wraps ExpandedWidth,
                color = card.upSide.color
            ) {
                Container {
                    Stack {
                        if (card.upSide.elements.isEmpty()) {
                            // TODO avoids an exception when a model changes upside.elements to zero length
                            //  java.lang.IllegalStateException: Expected a group start
                            //        at androidx.compose.SlotTableKt.getAsGroupStart(SlotTable.kt:641)
                            expanded { }
                        }
                        for (element in card.upSide.elements) {
                            aligned(Alignment.Center) {
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
}

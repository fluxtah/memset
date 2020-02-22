package com.citizenwarwick.ui.card.elements

import androidx.compose.Composable
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.unit.dp
import com.citizenwarwick.fretboard.Fretboard
import com.citizenwarwick.fretboard.FretboardMarker
import com.citizenwarwick.fretboard.FretboardMarker.FrettedNote
import com.citizenwarwick.fretboard.GuitarChord
import com.citizenwarwick.fretboard.encodeFingering
import com.citizenwarwick.fretboard.fingering
import com.citizenwarwick.fretboard.replaceOnSameString
import com.citizenwarwick.memset.core.model.FretboardElement
import com.citizenwarwick.ui.card.SelectionBorder

@Composable
fun FretboardElement(element: FretboardElement, isSelected: Boolean = false) {
    if (isSelected) {
        SelectionBorder {
            FretboardContainer(element)
        }
    } else {
        FretboardContainer(element)
    }
}

@Composable
private fun FretboardContainer(element: FretboardElement) {
    val spacing =
        LayoutPadding(element.spacingLeft.dp, element.spacingTop.dp, element.spacingRight.dp, element.spacingBottom.dp)

    val onFretboardPressed: (Int, Int) -> Unit = { string, fret ->
        val markers: MutableList<FretboardMarker> = if (element.markers.isEmpty()) {
            mutableListOf()
        } else {
            element.markers.fingering.toMutableList()
        }

        markers.replaceOnSameString(FrettedNote(string, fret))
        element.markers = markers.encodeFingering
    }

    Container(modifier = spacing) {
        if (element.markers.isEmpty()) {
            Fretboard(
                fromFret = 0,
                toFret = 6,
                scale = element.scale,
                onFretboardPressed = onFretboardPressed
            )
        } else {
            GuitarChord(
                fretboardMarkers = element.markers.fingering,
                scale = element.scale,
                onFretboardPressed = onFretboardPressed
            )
        }
    }
}

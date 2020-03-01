package com.citizenwarwick.ui.card.elements

import androidx.compose.Composable
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.unit.dp
import com.citizenwarwick.fretboard.Fretboard
import com.citizenwarwick.fretboard.FretboardMarker
import com.citizenwarwick.fretboard.FretboardMarker.FrettedNote
import com.citizenwarwick.fretboard.FretboardMarker.MutedString
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
        addChordNote(element, string, fret)
    }

    Container(modifier = spacing) {
        Fretboard(
            fromFret = element.startFret,
            toFret = element.endFret,
            scale = element.scale,
            fretboardMarkers = if (element.markers.isEmpty()) listOf() else element.markers.fingering,
            onFretboardPressed = onFretboardPressed
        )
    }
}

private fun addChordNote(
    element: FretboardElement,
    string: Int,
    fret: Int
) {
    val markers: MutableList<FretboardMarker> = if (element.markers.isEmpty()) {
        mutableListOf()
    } else {
        element.markers.fingering.toMutableList()
    }
    val existingMarker = markers.filterIsInstance<FrettedNote>().firstOrNull {
        it.stringNumber == string && it.fretNumber == fret
    }
    val existingMute = markers.filterIsInstance<MutedString>().firstOrNull {
        it.stringNumber == string
    }

    if (existingMarker != null) {
        if (existingMarker.fretNumber == 0) {
            markers.remove(existingMarker)
            markers.add(MutedString(existingMarker.stringNumber))
        } else {
            markers.remove(existingMarker)
        }
    } else if (existingMute != null) {
        markers.remove(existingMute)
        if (fret > 0) {
            markers.add(FrettedNote(string, fret))
        }
    } else {
        markers.replaceOnSameString(FrettedNote(string, fret))
    }

    element.markers = markers.encodeFingering(6)
}

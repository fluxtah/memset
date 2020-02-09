package com.citizenwarwick.ui.card.elements

import androidx.compose.Composable
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.citizenwarwick.memset.core.model.PianoRollElement
import com.citizenwarwick.music.Note
import com.citizenwarwick.music.PitchClass.C
import com.citizenwarwick.music.chord
import com.citizenwarwick.pianoroll.PianoChord
import com.citizenwarwick.pianoroll.PianoRoll
import com.citizenwarwick.ui.card.SelectionBorder

@Composable
fun PianoRollElement(element: PianoRollElement, isSelected: Boolean = false) {
    if (isSelected) {
        SelectionBorder {
            PianoChordContainer(element)
        }
    } else {
        PianoChordContainer(element)
    }
}

@Composable
private fun PianoChordContainer(element: PianoRollElement) {
    val spacing =
        LayoutPadding(element.spacingLeft.dp, element.spacingTop.dp, element.spacingRight.dp, element.spacingBottom.dp)

    val onKeyPressed: (Note) -> Unit = { key ->
        val chord = if (element.highlightedNotes.isEmpty()) {
            mutableListOf()
        } else {
            element.highlightedNotes.chord.toMutableList()
        }
        if (chord.contains(key)) {
            chord.remove(key)
        } else {
            chord.add(key)
        }
        element.highlightedNotes = chord.joinToString(" ") { it.toString() }
    }

    Container(modifier = spacing) {
        if (element.highlightedNotes.isEmpty()) {
            PianoRoll(
                from = Note(C, 0),
                to = Note(C, 0),
                sizeScale = element.scale,
                onKeyPressed = onKeyPressed
            )
        } else {
            PianoChord(chord = element.highlightedNotes.chord, sizeScale = element.scale, onKeyPressed = onKeyPressed)
        }
    }
}

@Composable
@Preview
fun PianoRollPreview() {
    PianoRoll(from = Note(C, 0), to = Note(C, 0), showNoteNames = true)
}

/**
 * Copyright 2020 Ian Warwick
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.citizenwarwick.features.cardeditor.model

import androidx.compose.Model
import androidx.compose.frames.ModelList
import androidx.compose.frames.modelListOf
import androidx.ui.core.Alignment
import androidx.ui.graphics.Color
import androidx.ui.text.font.FontWeight
import java.util.UUID

@Model
data class MemoryCard(
    val front: CardSurface = CardSurface(),
    val back: CardSurface = CardSurface(),
    var facingFront: Boolean = true
) {
    val upSide: CardSurface
        get() = if (facingFront) front else back
}

@Model
data class CardSurface(
    val elements: ModelList<MemoryCardElement> = modelListOf(),
    var color: Color = Color.White
)

/**
 * Generates a uuid so elements can be identified
 * even if they have the same properties (other than this uid!)
 */
fun generateElementUUID() = UUID.randomUUID().toString()

interface MemoryCardElement {
    val guid: String
    var name: String
    var alignment: Alignment
}

@Model
data class TextElement(
    override val guid: String = generateElementUUID(),
    override var name: String,
    override var alignment: Alignment = Alignment.Center,
    var text: String,
    var fontSize: Float = 8f,
    var spacingTop: Float = 0f,
    var spacingLeft: Float = 0f,
    var spacingRight: Float = 0f,
    var spacingBottom: Float = 0f,
    var color: Color = Color.Black,
    var fontWeight: FontWeight = FontWeight.Normal
) : MemoryCardElement

@Model
data class OvalShapeElement(
    override val guid: String = generateElementUUID(),
    override var name: String,
    override var alignment: Alignment = Alignment.Center,
    var width: Float = 64f,
    var height: Float = 64f,
    var color: Color = Color.Blue,
    var spacingTop: Float = 0f,
    var spacingRight: Float = 0f,
    var spacingBottom: Float = 0f,
    var spacingLeft: Float = 0f
) : MemoryCardElement

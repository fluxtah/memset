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
package com.citizenwarwick.memset.core.model

import androidx.compose.Model
import androidx.compose.frames.modelListOf
import androidx.ui.core.Alignment
import androidx.ui.graphics.Color
import androidx.ui.text.font.FontWeight
import java.util.UUID

@Model
data class MemoryCard(
    val front: CardSurface = CardSurface(),
    val back: CardSurface = CardSurface(),
    var designerSurfaceWidth: Float = 0f,
    var designerSurfaceHeight: Float = 0f,
    var facingFront: Boolean = true
) {
    val upSide: CardSurface
        get() = if (facingFront) front else back
}

@Model
data class CardSurface(
    val elements: MutableList<MemoryCardElement> = modelListOf(),
    var color: Color = Color.White
) {
    fun moveElementUp(item: MemoryCardElement) {
        val index = elements.indexOf(item)
        if (index > 0) {
            val prevIndex = index - 1
            val prevElement = elements[prevIndex]
            elements[prevIndex] = item
            elements[index] = prevElement
        }
    }

    fun moveElementDown(item: MemoryCardElement) {
        val index = elements.indexOf(item)
        if (index < elements.size.dec()) {
            val nextIndex = index + 1
            val nextElement = elements[nextIndex]
            elements[nextIndex] = item
            elements[index] = nextElement
        }
    }
}

/**
 * Generates a uuid so elements can be identified
 * even if they have the same properties (other than this uid!)
 */
fun generateElementUUID() = UUID.randomUUID().toString()

interface MemoryCardElement {
    /**
     * For editor use
     */
    val editorGuid: String
    var name: String
    var alignment: Alignment
    var spacingTop: Float
    var spacingLeft: Float
    var spacingRight: Float
    var spacingBottom: Float
}

@Model
data class TextElement(
    override val editorGuid: String = generateElementUUID(),
    override var name: String = "",
    override var alignment: Alignment = Alignment.Center,
    override var spacingTop: Float = 0f,
    override var spacingLeft: Float = 0f,
    override var spacingRight: Float = 0f,
    override var spacingBottom: Float = 0f,
    var color: Color = Color.Black,
    var text: String = "",
    var fontSize: Float = 8f,
    var fontWeight: FontWeight = FontWeight.Normal
) : MemoryCardElement

@Model
data class ShapeElement(
    override val editorGuid: String = generateElementUUID(),
    override var name: String = "",
    override var alignment: Alignment = Alignment.Center,
    override var spacingTop: Float = 0f,
    override var spacingLeft: Float = 0f,
    override var spacingRight: Float = 0f,
    override var spacingBottom: Float = 0f,

    var width: Float = 64f,
    var height: Float = 64f,
    var color: Color = Color.Blue,
    var shapeType: ShapeType = ShapeType.Oval
) : MemoryCardElement

enum class ShapeType {
    Oval,
    Rectangle
}

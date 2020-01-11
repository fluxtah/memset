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
import androidx.compose.frames.ModelMap
import androidx.compose.frames.modelMapOf
import androidx.ui.core.Alignment
import androidx.ui.graphics.Color

@Model
data class MemoryCard(
    val front: CardSurface,
    val back: CardSurface,
    val facingFront: Boolean = true
) {
    val upSide: CardSurface
        get() = if (facingFront) front else back
}

@Model
data class CardSurface(
    val elements: List<MemoryCardElement>,
    var color: Color = Color.White
)

interface MemoryCardElement {
    var name: String
}

@Model
data class TextElement(
    override var name: String,
    var text: String,
    var alignment: Alignment,
    var textSize: Float = 8f,
    var spacingTop: Float = 0f,
    var spacingLeft: Float = 0f,
    var spacingRight: Float = 0f,
    var spacingBottom: Float = 0f,
    var color: Color = Color.Blue
) : MemoryCardElement

@Model
data class OvalShapeElement(
    override var name: String,
    var width: Float = 64f,
    var height: Float = 64f,
    var color: Color = Color.Blue
) : MemoryCardElement

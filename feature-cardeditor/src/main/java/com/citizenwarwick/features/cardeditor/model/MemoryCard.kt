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
    val properties: ModelMap<String, String> = modelMapOf(),
    val elements: List<MemoryCardElement>
)

@Model
data class MemoryCardElement(val type: String, val properties: ModelMap<String, String> = modelMapOf())

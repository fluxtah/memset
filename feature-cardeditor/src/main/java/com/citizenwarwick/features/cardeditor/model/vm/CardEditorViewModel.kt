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
package com.citizenwarwick.features.cardeditor.model.vm

import androidx.lifecycle.ViewModel
import com.citizenwarwick.features.cardeditor.model.CardEditorModel
import com.citizenwarwick.features.cardeditor.model.CardEditorState
import com.citizenwarwick.features.cardeditor.model.LoadingState
import com.citizenwarwick.features.cardeditor.model.MemoryCard
import com.citizenwarwick.features.cardeditor.model.MemoryCardElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CardEditorViewModel : ViewModel(), CardEditorModel {
    override val state: CardEditorState =
        CardEditorState(
            loadingState = LoadingState.Loaded,
            card = MemoryCard()
        )

    override fun removeElement(item: MemoryCardElement) {
        getUpSideElements().remove(item)
    }

    override fun moveElementUp(item: MemoryCardElement) {
        val elements = getUpSideElements()
        val index = elements.indexOf(item)
        if (index > 0) {
            val prevIndex = index - 1
            val prevElement = elements[prevIndex]
            elements[prevIndex] = item
            elements[index] = prevElement
        }
    }

    override fun moveElementDown(item: MemoryCardElement) {
        val elements = getUpSideElements()
        val index = elements.indexOf(item)
        if (index < elements.size.dec()) {
            val nextIndex = index + 1
            val nextElement = elements[nextIndex]
            elements[nextIndex] = item
            elements[index] = nextElement
        }
    }

    private fun getUpSideElements(): MutableList<MemoryCardElement> =
        if (isCardFacingFront()) state.card.front.elements else state.card.back.elements

    private fun isCardFacingFront(): Boolean = state.card.upSide.elements == state.card.front.elements

    init {
        state.loadingState = LoadingState.Loading

        GlobalScope.launch(Dispatchers.IO) {
            delay(2000)
            GlobalScope.launch(Dispatchers.Main) {
                state.loadingState = LoadingState.Loaded
            }
        }

    }
}


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
package com.citizenwarwick.features.carddesigner.model.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citizenwarwick.features.carddesigner.model.CardDesignerModel
import com.citizenwarwick.memset.core.model.LoadingState
import com.citizenwarwick.features.carddesigner.model.MemoryCardEditorState
import com.citizenwarwick.memset.core.MemoryCardRepository
import com.citizenwarwick.memset.core.di.get
import com.citizenwarwick.memset.core.model.MemoryCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CardDesignerViewModel(
    private val repository: MemoryCardRepository = get()
) : ViewModel(), CardDesignerModel {
    override val state: MemoryCardEditorState =
        MemoryCardEditorState(
            loadingState = LoadingState.Loaded,
            card = MemoryCard()
        )

    override fun saveCard() {
        viewModelScope.launch {
            repository.saveCard(state.card)
        }
    }

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


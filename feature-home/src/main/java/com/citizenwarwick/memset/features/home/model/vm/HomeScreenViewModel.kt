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
package com.citizenwarwick.memset.features.home.model.vm

import androidx.compose.frames.modelListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.citizenwarwick.memset.core.MemoryCardRepository
import com.citizenwarwick.memset.core.di.get
import com.citizenwarwick.memset.core.model.LoadingState
import com.citizenwarwick.memset.core.model.MemoryCard
import com.citizenwarwick.memset.features.home.model.HomeScreenModel
import com.citizenwarwick.memset.features.home.model.HomeScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val repository: MemoryCardRepository = get()
) : ViewModel(), HomeScreenModel {
    override val state: HomeScreenState = HomeScreenState()
    private val cardsData = repository.getCards()
    private val cardsDataObserver = fun(it: List<MemoryCard>) {
        state.cards = modelListOf(*it.toTypedArray())
        state.loadingState = LoadingState.Loaded
    }

    override fun deleteCard(card: MemoryCard) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.deleteCard(card)
            // reload
            state.loadingState = LoadingState.Loaded
        }
    }

    init {
        cardsData.observeForever(cardsDataObserver)
    }

    override fun onCleared() {
        cardsData.removeObserver(cardsDataObserver)
    }
}

